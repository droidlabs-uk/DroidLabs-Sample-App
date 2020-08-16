package com.blockchain.transactionsChallenge.navigation;

import android.content.Context
import com.blockchain.breakingbad.ui.BreakingBadActivity
import com.blockchain.core.navigation.NavigationDest
import com.blockchain.core.navigation.Navigator
import javax.inject.Inject

class NavigatorImpl @Inject constructor() : Navigator {
    override fun navigateTo(navigationDest: NavigationDest, context: Context) =
        when (navigationDest) {
            NavigationDest.BreakingBad -> BreakingBadActivity.launchActivity(context)
        }

}