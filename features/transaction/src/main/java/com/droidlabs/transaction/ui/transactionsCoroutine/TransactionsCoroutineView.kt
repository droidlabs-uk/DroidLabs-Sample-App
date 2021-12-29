package com.droidlabs.transaction.ui.transactionsCoroutine

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.droidlabs.core.compose.ErrorScreen
import com.droidlabs.core.compose.LoadingScreen
import com.droidlabs.core.network.Result.Error
import com.droidlabs.core.network.Result.Loading
import com.droidlabs.core.network.Result.Success
import com.droidlabs.core.network.transaction.domain.model.Txs
import com.droidlabs.transaction.ui.compose.TransactionComposeList
import com.droidlabs.transaction.ui.compose.TransactionItemViewState

@Composable
fun TransactionsCoroutineView() {
    val viewModel = hiltViewModel<TransactionViewModel>()

    when (val result = viewModel.getTransactionsFlow.collectAsState().value) {
        is Loading -> LoadingScreen()
        is Success -> TransactionComposeList(result.data.mapToTransactionComposeList())
        is Error -> ErrorScreen(result.exception.localizedMessage)
    }
}

private fun List<Txs>.mapToTransactionComposeList(): List<TransactionItemViewState> =
    map {
        TransactionItemViewState(
            text = it.hash,
            time = it.time,
            amount = it.result
        )
    }
