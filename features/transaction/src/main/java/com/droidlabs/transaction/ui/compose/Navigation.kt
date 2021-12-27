package com.droidlabs.transaction.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.droidlabs.transaction.ui.transactionsFragmentCoroutine.TransactionViewModel

@Composable
fun TransactionActivityView() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = TransactionScreens.RxScreen.route
    ) {
        composable(route = TransactionScreens.RxScreen.route) {
            RxScreen(navController)
        }

        composable(route = TransactionScreens.CoroutineScreen.route) {
            CoroutineScreen(navController)
        }
    }
}

@Composable
fun ScaffoldScreen(navController: NavHostController, screen: @Composable () -> Unit) {

    val bottomNavigationItems = listOf(
        TransactionScreens.RxScreen,
        TransactionScreens.CoroutineScreen
    )

    Scaffold(
        topBar = {},
        bottomBar = { BottomAppNavBar(navController, bottomNavigationItems) },
        content = { screen() }
    )
}

@Composable
fun BottomAppNavBar(
    navController: NavHostController,
    bottomNavigationItems: List<TransactionScreens>
) {
    BottomAppBar(
        backgroundColor = Color.White,
        contentColor = Color.Red,
        elevation = 10.dp,
    ) {
        bottomNavigationItems.forEach { screen ->
            val navBackStackEntry by navController.currentBackStackEntryAsState()
//            val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

            BottomNavigationItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                selected = /*currentRoute == screen.route*/ false,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Composable
fun RxScreen(navController: NavHostController) {
    ScaffoldScreen(navController) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Rx Screen")

        }
    }
}

@Composable
fun CoroutineScreen(
    navController: NavHostController
) {
    ScaffoldScreen(navController) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Coroutine Screen")
            TransactionsFragmentCoroutineView()
        }
    }
}


@Composable
private fun TransactionsFragmentCoroutineView() {
    val viewModel = hiltViewModel<TransactionViewModel>()

    viewModel.fetchMultiAddress("xpub6CfLQa8fLgtouvLxrb8EtvjbXfoC1yqzH6YbTJw4dP7srt523AhcMV8Uh4K3TWSHz9oDWmn9MuJogzdGU3ncxkBsAC9wFBLmFrWT9Ek81kQ")

    val transactions by viewModel.multiAddressLiveData.observeAsState()

    transactions?.txs?.let {
        when {
            it.isEmpty() -> TransactionComposeList(itemViewStates = listOf())
            else -> TransactionComposeList(itemViewStates = it.toViewState())
        }
    }
}

sealed class TransactionScreens(val route: String, val label: String, val icon: ImageVector) {
    object RxScreen : TransactionScreens("rx", "Rx", Icons.Outlined.Home)
    object CoroutineScreen : TransactionScreens("coroutine", "Coroutine", Icons.Outlined.Explore)
}
