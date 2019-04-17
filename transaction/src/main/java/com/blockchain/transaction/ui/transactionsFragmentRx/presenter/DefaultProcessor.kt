package com.blockchain.transaction.ui.transactionsFragmentRx.presenter

import com.blockchain.transaction.interactor.ITransactionsInteractor
import com.blockchain.transaction.ui.transactionsFragmentRx.events.*
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.lang.kotlin.ofType
import rx.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class DefaultTransactionProcessor @Inject constructor(interactor: ITransactionsInteractor, scheduler: Scheduler) :
    ITransactionProcessor {

    private val transactionProcessor =
        Observable.Transformer<LoadTransactionsAction, TransactionsListState> { transformer ->
            transformer.switchMap { action ->
                interactor
                    .getTransactions(action.address)
                    .observeOn(scheduler)
                    .distinctUntilChanged{prev, curr ->
                        Arrays.deepEquals(
                            prev.toTypedArray(),
                            curr.toTypedArray()
                        )
                    }
                    .map { TransactionsListSuccessState(it) as TransactionsListState }
                    .onErrorReturn { TransactionsListErrorState(it) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }

    private fun listActionTransformer(action: TransactionsListActions): Observable<TransactionsListState> =
        when (action) {
            is LoadTransactionsAction -> {
                Observable
                    .just(action)
                    .ofType<LoadTransactionsAction>()
                    .compose(transactionProcessor)
            }
        }

    private fun isLoadingAction(listState: TransactionsListState): Boolean =
        when (listState) {
            is TransactionsListSuccessState,
            is TransactionsListErrorState -> false

            is TransactionsListLoadingState -> true
        }

    private val transactionsList =
        Observable.Transformer<TransactionsListContentAction, TransactionsListPartialState> { transformer ->
            transformer.publish { published ->
                published.map { it.list.map(this::listActionTransformer) }
                    .switchMap { events ->
                        Observable.combineLatest(events) { eventItem ->
                            TransactionsList(eventItem.map { it as TransactionsListState }) }
                            .map { if (it.listOfPartialStates.any(this::isLoadingAction)) TransactionsListLoading else it }
                    }
            }
        }

    override fun actionProcessor(): Observable.Transformer<TransactionAction, TransactionsListPartialState> =
        Observable.Transformer { action ->
            action.publish { shared ->
                Observable.merge<TransactionsListPartialState>(
                    shared.ofType<InitialAction>().map { TransactionsListInitialState(it.list) },
                    shared.ofType<TransactionsListContentAction>().compose(transactionsList)
                )
            }
        }
}

interface ITransactionProcessor {
    fun actionProcessor(): Observable.Transformer<TransactionAction, TransactionsListPartialState>
}