package com.blockchain.transaction

import com.blockchain.core.network.api.BlockchainAPI
import com.blockchain.core.network.api.datamodel.Txs
import com.blockchain.transaction.interactor.TransactionsInteractor
import com.blockchain.transaction.ui.transactionsFragmentRx.events.InitialIntent
import com.blockchain.transaction.ui.transactionsFragmentRx.events.TransactionIntent
import com.blockchain.transaction.ui.transactionsFragmentRx.events.TransactionViewState
import com.blockchain.transaction.ui.transactionsFragmentRx.events.TransactionsListInitialState
import com.blockchain.transaction.ui.transactionsFragmentRx.presenter.DefaultTransactionProcessor
import com.blockchain.transaction.ui.transactionsFragmentRx.presenter.TransactionPresenter
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import rx.Observable
import rx.observers.TestSubscriber
import rx.schedulers.Schedulers

class TransactionPresenterTest {

    private val blockchainAPI = mockk<BlockchainAPI>()
    private val interactor = mockk<TransactionsInteractor>()
    private val processor = mockk<DefaultTransactionProcessor>()

    private lateinit var transactionPresenter: TransactionPresenter

//    val testSubscriber = TestSubscriber<TransactionViewState>()

    // Trigger an event and check if the viewstate is equal what you expected

//    @Before
//    fun setup() {
//
//        every { processor.actionProcessor() } returns Observable.Transformer { Observable.just(TransactionsListInitialState(listOf())) }
//
//        transactionPresenter = TransactionPresenter(processor)
//
//        transactionPresenter.state
//            .observeOn(Schedulers.immediate())
//            .subscribe(testSubscriber)
//
//    }


    @Test
    fun `test the InitialState`() {
        //GIVEN
        val emptyTxsList = listOf<Txs>()
        val expectedViewState = TransactionViewState(emptyTxsList, transactionsLoading = false)

        val testSubscriber = TestSubscriber<TransactionViewState>()

        every { processor.actionProcessor() } returns Observable.Transformer { Observable.just(TransactionsListInitialState(emptyTxsList)) }

        transactionPresenter = TransactionPresenter(processor)

        transactionPresenter.state
            .observeOn(Schedulers.trampoline())
            .subscribe(testSubscriber)

        //WHEN
        viewIntents("").subscribe(transactionPresenter.binder)

        //THEN
        val resultViewState = testSubscriber.onNextEvents.takeLast(1)

        assertEquals(expectedViewState, resultViewState)
    }

    private fun viewIntents(address: String): Observable<TransactionIntent> =
        Observable.just(InitialIntent(address))
}