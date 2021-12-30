package com.droidlabs.transaction.ui.transactionsRx.events

sealed class TransactionIntent
data class InitialIntent(val address: String): TransactionIntent()
