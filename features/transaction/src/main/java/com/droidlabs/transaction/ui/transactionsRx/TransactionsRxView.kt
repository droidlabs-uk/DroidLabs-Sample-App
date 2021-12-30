package com.droidlabs.transaction.ui.transactionsRx

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.droidlabs.core.compose.ErrorScreen
import com.droidlabs.core.compose.LoadingScreen
import com.droidlabs.transaction.ui.compose.TransactionComposeList
import com.droidlabs.transaction.ui.compose.toViewState
import com.droidlabs.transaction.ui.transactionsCoroutine.TransactionViewModel
import com.droidlabs.transaction.ui.transactionsRx.events.TransactionViewState
import dagger.hilt.EntryPoint
import javax.inject.Inject

@Composable
fun TransactionsRxView() {
    //haxx to inject 'presenter.state' into composable
    val viewModel = hiltViewModel<RxViewModel>()

    val viewState = viewModel.viewState.subscribeAsState(initial = TransactionViewState()).value

    if (viewState.transactionsError.isError) ErrorScreen(errorMessage = viewState.transactionsError.message)

    when {
        viewState.isLoading() -> LoadingScreen()
        else -> TransactionComposeList(itemViewStates = viewState.transactions.toViewState())
    }
}