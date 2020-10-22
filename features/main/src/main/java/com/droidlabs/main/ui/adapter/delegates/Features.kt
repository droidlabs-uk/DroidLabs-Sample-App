package com.droidlabs.main.ui.adapter.delegates

import android.widget.TextView
import com.droidlabs.main.R
import com.droidlabs.main.ui.adapter.MainActions
import com.droidlabs.main.ui.adapter.MainItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate

fun cuvvaFeature(clicks: (MainActions) -> Unit) = adapterDelegate<MainItem.CuvvaFeature, MainItem>(
    R.layout.main_activity_feature
) {
    val featureTitle: TextView = findViewById(R.id.main_activity_feature_title)

    bind {
        featureTitle.setText(R.string.cuvvaFeature)
        itemView.setOnClickListener {
            clicks(MainActions.CuvvaFeatureClicked)
        }
    }
}

fun transactionsFeature(clicks: (MainActions) -> Unit) =
    adapterDelegate<MainItem.TransactionsFeature, MainItem>(
        R.layout.main_activity_feature
    ) {
        val featureTitle: TextView = findViewById(R.id.main_activity_feature_title)

        bind {
            featureTitle.setText(R.string.transactionsFeature)
            itemView.setOnClickListener {
                clicks(MainActions.TransactionsFeatureClicked)
            }
        }
    }

fun breakingBadFeature(clicks: (MainActions) -> Unit) =
    adapterDelegate<MainItem.BreakingBadFeature, MainItem>(
        R.layout.main_activity_feature
    ) {
        val featureTitle: TextView = findViewById(R.id.main_activity_feature_title)

        bind {
            featureTitle.setText(R.string.breakingBadFeature)
            itemView.setOnClickListener {
                clicks(MainActions.BreakingBadFeatureClicked)
            }
        }
    }
