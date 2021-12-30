package com.droidlabs.transaction.ui.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.droidlabs.core.compose.ScaffoldScreen
import com.droidlabs.core.compose.Screen
import com.droidlabs.transaction.ui.transactionsCoroutine.TransactionViewModel
import com.droidlabs.transaction.ui.transactionsCoroutine.TransactionsCoroutineView
import com.droidlabs.transaction.ui.transactionsRx.TransactionsRxView
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class TransactionScreens(
    override val route: String,
    override val label: String,
    override val icon: ImageVector
) : Screen {
    object RxScreen : TransactionScreens("rx", "Rx", Icons.Outlined.Home)
    object CoroutineScreen : TransactionScreens("coroutine", "Coroutine", Icons.Outlined.Explore)
}

@Composable
fun RxScreen(navController: NavHostController, bottomNavigationItems: List<Screen>) {
    ScaffoldScreen(navController, bottomNavigationItems) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Rx Screen")
            TransactionsRxView()
        }
    }
}

@Composable
fun CoroutineScreen(
    navController: NavHostController,
    bottomNavigationItems: List<Screen>
) {
    val viewModel = hiltViewModel<TransactionViewModel>()
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    ScaffoldScreen(navController, bottomNavigationItems) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(viewModel.getRefreshState().value),
            onRefresh = {
                coroutineScope.launch(Dispatchers.Main) { scrollState.scrollToItem(0) }
                viewModel.refresh()
            })
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Coroutine Screen")
                TransactionsCoroutineView()
            }
        }
    }
}