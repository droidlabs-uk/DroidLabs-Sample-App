package com.droidlabs.transaction

import com.droidlabs.core.network.api.datamodel.Txs
import com.droidlabs.transaction.interactor.TransactionsInteractor
import com.droidlabs.transaction.ui.transactionsFragmentRx.events.ErrorViewState
import com.droidlabs.transaction.ui.transactionsFragmentRx.events.TransactionViewState
import com.droidlabs.transaction.ui.transactionsFragmentRx.presenter.DefaultTransactionProcessor
import com.droidlabs.transaction.ui.transactionsFragmentRx.presenter.TransactionPresenter
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Flowable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subscribers.TestSubscriber
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test


class TransactionPresenterTest {

    private val testScheduler = TestScheduler()

    private val interactor = mockk<TransactionsInteractor>()
    private val processor = DefaultTransactionProcessor(interactor, testScheduler)

    private lateinit var transactionPresenter: TransactionPresenter
    private lateinit var testSubscriber: TestSubscriber<TransactionViewState>

    private val emptyTxsList = listOf<Txs>()
    private val address = ""


    // Trigger an event and check if the viewstate is equal what you expected

    companion object {
        @BeforeClass @JvmStatic
        fun setupClass() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        }
    }

    @Before
    fun setup() {
        transactionPresenter = TransactionPresenter(processor, testScheduler)

        testSubscriber = transactionPresenter.state.test()
    }

    @After
    fun cleanup() = testSubscriber.dispose()

    @Test
    fun `test success state`() {
        //GIVEN
        val expectedViewState = TransactionViewState(emptyTxsList, transactionsLoading = false)

        every { interactor.getTransactions(address) } returns Flowable.just(emptyTxsList)

        //THEN
        testSubscriber.assertNoErrors()
        testSubscriber.assertValue(expectedViewState)
    }

    @Test
    fun `test error state`(){
        //GIVEN
        val errorMessage = "ERROR"
        val errorViewState = ErrorViewState(isError = true, message = errorMessage)
        val errorThrowable = Throwable(message = errorMessage)

        val errorTransactionViewState = TransactionViewState(emptyTxsList, errorViewState, transactionsLoading = false)

        every { interactor.getTransactions(address) } returns Flowable.error(errorThrowable)

        //WHEN

        //THEN
        testSubscriber.assertValueAt(1, errorTransactionViewState)
    }

    @Test
    fun `test loading state`(){
        //GIVEN
        val expectedViewState = TransactionViewState(emptyTxsList, transactionsLoading = true)

        every { interactor.getTransactions(address) } returns Flowable.empty<List<Txs>>()

        //WHEN

        //THEN
        testSubscriber.assertValue(expectedViewState)
    }
}