package com.blockchain.transaction.ui.transactionsFragmentRx.events

import com.blockchain.core.network.api.datamodel.Txs

sealed class TransactionsListPartialState
class TransactionsListInitialState(val transactions: List<Txs>): TransactionsListPartialState()

data class TransactionsList(val listOfPartialStates: List<TransactionsListState>): TransactionsListPartialState()
object TransactionsListLoading: TransactionsListPartialState()


sealed class TransactionsListState

data class TransactionsListSuccessState(val transactions: List<Txs>): TransactionsListState()
data class TransactionsListErrorState(val error: Throwable): TransactionsListState()
object TransactionsListLoadingState : TransactionsListState()