package com.blockchain.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var navController: NavController
    private lateinit var navGraph: NavGraph

//    private var fragment = TransactionsFragmentCoroutine()
//    private var fragment = TransactionsFragmentRx()
    private var fragment = HomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initNavGraph()
    }

    private fun initNavGraph() {
        navController = Navigation.findNavController(this, R.id.main_nav_host_fragment)
        navGraph = navController.navInflater.inflate(R.navigation.main_nav_graph)

        navController.setGraph(R.navigation.main_nav_graph)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector
}
