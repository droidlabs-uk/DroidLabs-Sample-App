package com.droidlabs.transaction.ui.transactionsRx.events

import com.droidlabs.core.network.transaction.domain.model.Txs

sealed class TransactionsListActions
data class LoadTransactionsAction(val address: String) : TransactionsListActions()

sealed class TransactionAction
data class InitialAction(val list: List<Txs>): TransactionAction()
data class TransactionsListContentAction(val list: List<TransactionsListActions>) : TransactionAction()