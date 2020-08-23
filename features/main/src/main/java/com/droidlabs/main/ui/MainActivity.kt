package com.droidlabs.main.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import com.droidlabs.main.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var navGraph: NavGraph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initNavGraph()
    }

    private fun initNavGraph() {
        navController = Navigation.findNavController(
            this,
            R.id.main_nav_host_fragment
        )
        navGraph = navController.navInflater.inflate(R.navigation.main_nav_graph)

        navController.setGraph(R.navigation.main_nav_graph)
    }
}
