package com.droidlabs.transaction.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.droidlabs.core.compose.ScaffoldScreen
import com.droidlabs.core.compose.Screen
import com.droidlabs.transaction.ui.transactionsCoroutine.TransactionsCoroutineView
import com.droidlabs.transaction.ui.transactionsRx.TransactionsRxView
import org.w3c.dom.Text

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
    ScaffoldScreen(navController, bottomNavigationItems) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Coroutine Screen")
            TransactionsCoroutineView()
        }
    }
}