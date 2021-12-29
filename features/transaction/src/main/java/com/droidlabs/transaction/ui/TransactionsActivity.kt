package com.droidlabs.transaction.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionsActivity : AppCompatActivity() {

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
