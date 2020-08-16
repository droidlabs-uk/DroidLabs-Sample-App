package com.blockchain.breakingbad.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import com.blockchain.breakingbad.R

class BreakingBadActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var navGraph: NavGraph

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