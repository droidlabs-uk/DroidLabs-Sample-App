package com.blockchain.transaction.ui.transactionsFragmentRx.presenter

import com.blockchain.transaction.ui.transactionsFragmentRx.events.*
import rx.Observable
import rx.Observer
import rx.Scheduler
import rx.lang.kotlin.ofType
import rx.schedulers.Schedulers
import rx.subjects.BehaviorSubject
import rx.subjects.PublishSubject
import javax.inject.Inject

class   TransactionPresenter @Inject constructor(processor: ITransactionProcessor, scheduler: Scheduler): ITransactionPresenter {

    private val _state: BehaviorSubject<TransactionViewState> = BehaviorSubject.create<TransactionViewState>()
    override val state: Observable<TransactionViewState>
        get() = _state

    private val _binder: PublishSubject<TransactionIntent> = PublishSubject.create<TransactionIntent>()
    override val binder: Observer<TransactionIntent>
        get() = _binder

    init {
        _binder
            .observeOn(scheduler)
            .compose(intentFilter())
            .flatMap { intentToAction(it) }
            .compose(processor.actionProcessor())
            .scan(TransactionViewState(), this::reducer)
            .distinctUntilChanged()
            .subscribe(_state)
    }

    private fun intentFilter(): Observable.Transformer<TransactionIntent, TransactionIntent> {
        return Observable.Transformer { stream ->
            stream.publish { intent ->
                Observable.merge(
                    intent.ofType<InitialIntent>().distinctUntilChanged { prev, curr -> prev == curr },
                    intent.filter { it !is InitialIntent }
                )
            }
        }
    }

    private fun intentToAction(intent: TransactionIntent): Observable<TransactionAction> {
        return when (intent) {
            is InitialIntent -> {
                Observable.concat(
                    Observable.just(InitialAction(listOf())),
                    Observable.just(TransactionsListContentAction(list = updateActions(intent.address)))
                )
            }
        }
    }

    private fun updateActions(address: String): List<TransactionsListActions> {
        val actionList = mutableListOf<TransactionsListActions>()

        actionList.add(LoadTransactionsAction(address))

        return actionList
    }

    private fun reducer(viewState: TransactionViewState, event: TransactionsListPartialState): TransactionViewState =
        when (event) {
            is TransactionsListInitialState -> viewState.copy(transactions = event.transactions)

            is TransactionsList -> event.listOfPartialStates.fold(viewState){ state, transactionsListPartialState ->
                when(transactionsListPartialState){
                    is TransactionsListSuccessState -> state.copy(
                        transactionsLoading = false,
                        transactions = transactionsListPartialState.transactions
                    )

                    is TransactionsListErrorState -> state.copy(
                        transactionsError = ErrorViewState(
                            isError = true,
                            message = transactionsListPartialState.error.localizedMessage
                        )
                    )

                    TransactionsListLoadingState -> state.copy(transactionsLoading = true)
                }
            }.copy(transactionsLoading = false)

            TransactionsListLoading -> viewState.copy(transactionsLoading = true)
        }
}

interface ITransactionPresenter {
    val binder: Observer<TransactionIntent>
    val state: Observable<TransactionViewState>
}