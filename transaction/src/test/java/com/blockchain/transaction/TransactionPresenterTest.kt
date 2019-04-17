package com.blockchain.transaction

import com.blockchain.core.network.api.datamodel.Txs
import com.blockchain.transaction.interactor.TransactionsInteractor
import com.blockchain.transaction.ui.transactionsFragmentRx.events.ErrorViewState
import com.blockchain.transaction.ui.transactionsFragmentRx.events.InitialIntent
import com.blockchain.transaction.ui.transactionsFragmentRx.events.TransactionIntent
import com.blockchain.transaction.ui.transactionsFragmentRx.events.TransactionViewState
import com.blockchain.transaction.ui.transactionsFragmentRx.presenter.DefaultTransactionProcessor
import com.blockchain.transaction.ui.transactionsFragmentRx.presenter.TransactionPresenter
import io.mockk.Runs
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Flowable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test


class TransactionPresenterTest {

    private val testScheduler = Schedulers.trampoline()
    private val testSubscriber = TestSubscriber<TransactionViewState>()

    private val interactor = mockk<TransactionsInteractor>()
    private val processor = DefaultTransactionProcessor(interactor, testScheduler)

    private lateinit var transactionPresenter: TransactionPresenter


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

        transactionPresenter.state
            .observeOn(testScheduler)
            .subscribe(testSubscriber)
    }


    @Test
    fun `test success state`() {
        //GIVEN
        val emptyTxsList = listOf<Txs>()
        val expectedViewState = TransactionViewState(emptyTxsList, transactionsLoading = false)

        every { interactor.getTransactions("") } returns Flowable.just(emptyTxsList)

        //WHEN
        viewIntent()
            .subscribe(transactionPresenter.binder)

        //THEN
        testSubscriber.assertNoErrors()
        testSubscriber.assertValue(expectedViewState)
    }


    @Test
    fun `test error state`(){
        //GIVEN
        val emptyTxsList = listOf<Txs>()

        val errorMessage = "ERROR"
        val errorViewState = ErrorViewState(isError = true, message = errorMessage)
        val errorThrowable = Throwable(message = errorMessage)

        val expectedViewState = TransactionViewState(emptyTxsList, errorViewState, transactionsLoading = false)

        every { interactor.getTransactions("") } returns Flowable.error(errorThrowable)

        //WHEN
        viewIntent()
            .subscribe(transactionPresenter.binder)

        //THEN
        testSubscriber.assertError(errorThrowable)
        testSubscriber.assertValue(expectedViewState)
    }

    @Test
    fun `test loading state`(){
        //GIVEN
        val emptyTxsList = listOf<Txs>()

        val expectedViewState = TransactionViewState(emptyTxsList, transactionsLoading = true)

        every { interactor.getTransactions("") }

        //WHEN
        viewIntent()
            .subscribe(transactionPresenter.binder)

        //THEN
        testSubscriber.assertValue(expectedViewState)
    }

    private fun viewIntent(): Flowable<TransactionIntent> = Flowable.just(InitialIntent(""))
}