package com.blockchain.transactionsChallenge.navigation;

import android.content.Context
import android.widget.Toast
import com.blockchain.breakingbad.ui.BreakingBadActivity
import com.blockchain.core.navigation.NavigationDest
import com.blockchain.core.navigation.Navigator
import javax.inject.Inject

class NavigatorImpl @Inject constructor() : Navigator {
    override fun navigateTo(navigationDest: NavigationDest, context: Context) =
        when (navigationDest) {
            NavigationDest.BreakingBad -> BreakingBadActivity.launchActivity(context)
            NavigationDest.Cuvva -> showToast(context)
            NavigationDest.Transactions -> showToast(context)
            NavigationDest.Rx -> showToast(context)
            NavigationDest.Coroutine -> showToast(context)
        }

    // TODO()
    private fun showToast(context: Context) =
        Toast.makeText(context, "Not implemented - see NavigatorImpl", Toast.LENGTH_LONG).show()
}
