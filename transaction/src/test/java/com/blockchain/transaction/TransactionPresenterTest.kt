package com.blockchain.transaction

import com.blockchain.core.network.api.datamodel.Txs
import com.blockchain.transaction.interactor.TransactionsInteractor
import com.blockchain.transaction.ui.transactionsFragmentRx.events.InitialIntent
import com.blockchain.transaction.ui.transactionsFragmentRx.events.TransactionIntent
import com.blockchain.transaction.ui.transactionsFragmentRx.events.TransactionViewState
import com.blockchain.transaction.ui.transactionsFragmentRx.presenter.DefaultTransactionProcessor
import com.blockchain.transaction.ui.transactionsFragmentRx.presenter.TransactionPresenter
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test


class TransactionPresenterTest {

    private val testScheduler = Schedulers.trampoline()
    private val testSubscriber = TestObserver<TransactionViewState>()

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

        every { interactor.getTransactions("") } returns Observable.just(emptyTxsList)

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

        //WHEN

        //THEN
    }

    @Test
    fun `test loading state`(){
        //GIVEN

        //WHEN

        //THEN
    }

    private fun viewIntent(): Observable<TransactionIntent> = Observable.just(InitialIntent(""))
}