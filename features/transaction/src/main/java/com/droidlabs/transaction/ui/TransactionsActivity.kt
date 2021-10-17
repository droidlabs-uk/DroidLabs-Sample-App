package com.droidlabs.transaction.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.droidlabs.transaction.R
import com.droidlabs.transaction.ui.transactionsFragmentCoroutine.TransactionsFragmentCoroutine
import com.droidlabs.transaction.ui.transactionsFragmentRx.TransactionsFragmentRx
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionsActivity : AppCompatActivity() {

    private val transactionsFragmentRx = TransactionsFragmentRx()
    private val transactionsFragmentCoroutine by lazy { TransactionsFragmentCoroutine() }

    companion object {
        fun launchActivity(context: Context) {
            context.startActivity(
                Intent(
                    context,
                    TransactionsActivity::class.java
                )
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transactions)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)

        //TODO: better init for Fragments¬
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.transactions_fragment_container,
                transactionsFragmentRx
            ).commit()

    }

    private val navListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null

            when (item.itemId) {
                R.id.rx_fragment -> selectedFragment = transactionsFragmentRx
                R.id.coroutine_fragment -> selectedFragment = transactionsFragmentCoroutine
            }

            selectedFragment?.let {
                supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.transactions_fragment_container,
                        it
                    ).commit()
            }

            true
        }
}
