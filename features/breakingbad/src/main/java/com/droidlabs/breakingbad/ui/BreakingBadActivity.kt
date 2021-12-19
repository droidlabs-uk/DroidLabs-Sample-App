package com.droidlabs.breakingbad.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import com.droidlabs.breakingbad.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_breaking_bad.*

@AndroidEntryPoint
class BreakingBadActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var navGraph: NavGraph

    companion object {
        fun launchActivity(context: Context) {
            context.startActivity(
                Intent(
                    context,
                    BreakingBadActivity::class.java
                )
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breaking_bad)

        initNavGraph()

        activity_breaking_bad_toolbar.title = getString(R.string.breaking_bad_title)
        setSupportActionBar(activity_breaking_bad_toolbar)
    }

    private fun initNavGraph() {
        navController = Navigation.findNavController(this, R.id.bb_nav_host_fragment)
        navGraph = navController.navInflater.inflate(R.navigation.bb_nav_graph)

        navController.setGraph(R.navigation.bb_nav_graph)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}