package com.droidlabs.transaction.ui.transactionsCoroutine

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.droidlabs.core.compose.ErrorScreen
import com.droidlabs.core.compose.LoadingScreen
import com.droidlabs.core.network.Result.*
import com.droidlabs.transaction.ui.compose.TransactionComposeList
import com.droidlabs.transaction.ui.compose.toViewState

@Composable
fun TransactionsCoroutineView() {
    val viewModel = hiltViewModel<TransactionViewModel>()

    when (val result = viewModel.getTransactionsFlow.collectAsState().value) {
        is Loading -> LoadingScreen()
        is Success -> TransactionComposeList(result.data.toViewState())
        is Error -> ErrorScreen(result.exception.localizedMessage)
    }
}
