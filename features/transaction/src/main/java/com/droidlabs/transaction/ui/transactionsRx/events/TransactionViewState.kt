package com.droidlabs.transaction.ui.transactionsRx.events

import com.droidlabs.core.network.transaction.domain.model.Txs

data class TransactionViewState (
    val transactions: List<Txs> = listOf(),
    val transactionsError: ErrorViewState = ErrorViewState(),
    val transactionsLoading: Boolean = true
) {
    fun isLoading(): Boolean = transactionsLoading
}

data class ErrorViewState(val isError: Boolean = false, override val message: String = ""): Throwable()