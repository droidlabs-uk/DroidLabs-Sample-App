package com.droidlabs.transaction.ui.transactionsFragmentRx.events

import com.droidlabs.core.network.transaction.api.datamodel.Txs

sealed class TransactionsListPartialState
class TransactionsListInitialState(val transactions: List<Txs>): TransactionsListPartialState()

data class TransactionsList(val listOfPartialStates: List<TransactionsListState>): TransactionsListPartialState()
object TransactionsListLoading: TransactionsListPartialState()


sealed class TransactionsListState

data class TransactionsListSuccessState(val transactions: List<Txs>): TransactionsListState()
data class TransactionsListErrorState(val error: Throwable): TransactionsListState()
object TransactionsListLoadingState : TransactionsListState()