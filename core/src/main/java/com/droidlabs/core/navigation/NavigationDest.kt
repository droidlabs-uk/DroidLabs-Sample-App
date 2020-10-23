package com.droidlabs.core.navigation

sealed class NavigationDest {
    object BreakingBad : NavigationDest()
    object Cuvva : NavigationDest()
    object Transactions : NavigationDest()
}