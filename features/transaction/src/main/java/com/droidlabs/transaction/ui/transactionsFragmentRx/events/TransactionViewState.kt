package com.droidlabs.transaction.ui.transactionsFragmentRx.events

import com.droidlabs.core.network.transaction.api.datamodel.Txs

data class TransactionViewState (
    val transactions: List<Txs> = listOf(),
    val transactionsError: ErrorViewState = ErrorViewState(),
    val transactionsLoading: Boolean = false
) {
    fun isLoading(): Boolean = transactionsLoading
}

data class ErrorViewState(val isError: Boolean = false, override val message: String = ""): Throwable()