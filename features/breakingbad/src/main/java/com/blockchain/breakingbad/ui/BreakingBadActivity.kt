package com.blockchain.breakingbad.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import com.blockchain.breakingbad.R

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
    }

    private fun initNavGraph() {
        navController = Navigation.findNavController(this, R.id.bb_nav_host_fragment)
        navGraph = navController.navInflater.inflate(R.navigation.bb_nav_graph)

        navController.setGraph(R.navigation.bb_nav_graph)
    }
}