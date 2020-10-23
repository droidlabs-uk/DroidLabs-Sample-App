package com.droidlabs.cuvva.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.droidlabs.cuvva.R

class CuvvaActivity : AppCompatActivity() {
    companion object {
        fun launchActivity(context: Context) {
            context.startActivity(
                Intent(
                    context,
                    CuvvaActivity::class.java
                )
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuvva)
    }
}
