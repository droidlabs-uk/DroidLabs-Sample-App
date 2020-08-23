package com.droidlabs.transaction.ui.transactionsFragmentRx.events

sealed class TransactionIntent
data class InitialIntent(val address: String): TransactionIntent()
