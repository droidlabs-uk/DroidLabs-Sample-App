package com.droidlabs.transaction.ui.transactionsFragmentRx.presenter

import com.droidlabs.transaction.ui.transactionsFragmentRx.events.*
import io.reactivex.Flowable
import io.reactivex.FlowableSubscriber
import io.reactivex.FlowableTransformer
import io.reactivex.Scheduler
import io.reactivex.processors.PublishProcessor
import io.reactivex.processors.ReplayProcessor
import io.reactivex.rxkotlin.ofType
import javax.inject.Inject

class TransactionPresenter @Inject constructor(processor: ITransactionProcessor, scheduler: Scheduler): ITransactionPresenter {

    private val _state: ReplayProcessor<TransactionViewState> = ReplayProcessor.create<TransactionViewState>()
    override val state: Flowable<TransactionViewState>
        get() = _state

    private val _binder: PublishProcessor<TransactionIntent> = PublishProcessor.create()
    override val binder: FlowableSubscriber<TransactionIntent>
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

    private fun intentFilter(): FlowableTransformer<TransactionIntent, TransactionIntent> {
        return FlowableTransformer { stream ->
            stream.publish { intent ->
                Flowable.merge(
                    intent.ofType<InitialIntent>().distinctUntilChanged { prev, curr -> prev == curr },
                    intent.filter { it !is InitialIntent }
                )
            }
        }
    }

    private fun intentToAction(intent: TransactionIntent): Flowable<TransactionAction> {
        return when (intent) {
            is InitialIntent -> {
                Flowable.concat(
                    Flowable.just(InitialAction(listOf())),
                    Flowable.just(TransactionsListContentAction(list = updateActions(intent.address)))
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
    val binder: FlowableSubscriber<TransactionIntent>
    val state: Flowable<TransactionViewState>
}