package com.droidlabs.app.navigation;

import android.content.Context
import android.widget.Toast
import com.droidlabs.breakingbad.ui.BreakingBadActivity
import com.droidlabs.core.navigation.NavigationDest
import com.droidlabs.core.navigation.Navigator
import com.droidlabs.cuvva.ui.home.CuvvaActivity
import javax.inject.Inject

class NavigatorImpl @Inject constructor() : Navigator {
    override fun navigateTo(navigationDest: NavigationDest, context: Context) =
        when (navigationDest) {
            NavigationDest.BreakingBad -> BreakingBadActivity.launchActivity(context)
            NavigationDest.Cuvva -> CuvvaActivity.launchActivity(context, )
            NavigationDest.Transactions -> showToast(context)
            NavigationDest.Rx -> showToast(context)
            NavigationDest.Coroutine -> showToast(context)
        }

    // TODO()
    private fun showToast(context: Context) =
        Toast.makeText(context, "Not implemented - see NavigatorImpl", Toast.LENGTH_LONG).show()
}
