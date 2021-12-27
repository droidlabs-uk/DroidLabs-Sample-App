package com.droidlabs.transaction.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.fragment.app.Fragment
import com.droidlabs.transaction.R
import com.droidlabs.transaction.ui.compose.TransactionActivityView
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

        setContent {
            TransactionActivityView()
        }
    }
}
