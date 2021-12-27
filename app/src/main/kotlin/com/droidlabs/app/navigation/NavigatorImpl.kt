package com.droidlabs.app.navigation;

import android.content.Context
import android.widget.Toast
import com.droidlabs.breakingbad.ui.BreakingBadActivity
import com.droidlabs.core.navigation.NavigationDest
import com.droidlabs.core.navigation.Navigator
import com.droidlabs.cuvva.ui.home.CuvvaActivity
import com.droidlabs.transaction.ui.TransactionsActivity
import javax.inject.Inject

class NavigatorImpl @Inject constructor() : Navigator {
    override fun navigateTo(navigationDest: NavigationDest, context: Context) =
            when (navigationDest) {
                NavigationDest.BreakingBad -> BreakingBadActivity.launchActivity(context)
                NavigationDest.Cuvva -> CuvvaActivity.launchActivity(context)
                NavigationDest.Transactions -> TransactionsActivity.launchActivity(context)
            }
}
