package com.droidlabs.transaction.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.droidlabs.transaction.ui.compose.CoroutineScreen
import com.droidlabs.transaction.ui.compose.RxScreen
import com.droidlabs.transaction.ui.compose.TransactionScreens
import com.droidlabs.transaction.ui.compose.TransactionScreens.*

@Composable
fun TransactionActivityView() {
    val navController = rememberNavController()

    val bottomNavigationItems = listOf(
        RxScreen,
        CoroutineScreen
    )

    NavHost(
        navController = navController,
        startDestination = RxScreen.route
    ) {
        composable(route = RxScreen.route) {
            RxScreen(navController, bottomNavigationItems)
        }

        composable(route = CoroutineScreen.route) {
            CoroutineScreen(navController, bottomNavigationItems)
        }
    }
}