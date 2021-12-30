package com.droidlabs.core.compose

import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomAppNavBar(
    navController: NavHostController,
    bottomNavigationItems: List<Screen>
) {
    BottomAppBar(
        backgroundColor = Color.White,
        contentColor = Color.DarkGray,
        elevation = 20.dp,
    ) {
        bottomNavigationItems.forEach { screen ->
            val currentRoute = navController.currentBackStackEntry?.destination?.route

            BottomNavigationItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                selected = currentRoute == screen.route,
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
fun ScaffoldScreen(
    navController: NavHostController,
    bottomNavigationItems: List<Screen>,
    screen: @Composable () -> Unit
) {

    Scaffold(
        topBar = {},
        bottomBar = { BottomAppNavBar(navController, bottomNavigationItems) },
        content = { screen() }
    )
}

interface Screen {
    val route: String
    val label: String
    val icon: ImageVector
}