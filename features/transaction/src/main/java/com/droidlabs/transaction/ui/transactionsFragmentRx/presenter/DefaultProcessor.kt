package com.droidlabs.transaction.ui.transactionsFragmentRx.presenter

import com.droidlabs.transaction.interactor.ITransactionsInteractor
import com.droidlabs.transaction.ui.transactionsFragmentRx.events.*
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.ofType
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class DefaultTransactionProcessor @Inject constructor(interactor: ITransactionsInteractor, scheduler: Scheduler) :
    ITransactionProcessor {

    private val transactionProcessor =
        FlowableTransformer<LoadTransactionsAction, TransactionsListState> { transformer ->
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

    private fun listActionTransformer(action: TransactionsListActions): Flowable<TransactionsListState> =
        when (action) {
            is LoadTransactionsAction -> {
                Flowable
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
        FlowableTransformer<TransactionsListContentAction, TransactionsListPartialState> { transformer ->
            transformer.publish { published ->
                published.map { it.list.map(this::listActionTransformer) }
                    .switchMap { events ->
                        Flowable.combineLatest(events) { eventItem ->
                            TransactionsList(eventItem.map { it as TransactionsListState }) }
                            .map { if (it.listOfPartialStates.any(this::isLoadingAction)) TransactionsListLoading else it }
                    }
            }
        }

    override fun actionProcessor(): FlowableTransformer<TransactionAction, TransactionsListPartialState> =
        FlowableTransformer { action ->
            action.publish { shared ->
                Flowable.merge<TransactionsListPartialState>(
                    shared.ofType<InitialAction>().map { TransactionsListInitialState(it.list) },
                    shared.ofType<TransactionsListContentAction>().compose(transactionsList)
                )
            }
        }
}

interface ITransactionProcessor {
    fun actionProcessor(): FlowableTransformer<TransactionAction, TransactionsListPartialState>
}