package com.blockchain.main.ui.adapter.delegates

import android.widget.TextView
import com.blockchain.main.R
import com.blockchain.main.ui.adapter.MainItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate

fun cuvvaFeature() = adapterDelegate<MainItem.CuvvaFeature, MainItem>(
    R.layout.main_activity_feature
) {
    val featureTitle: TextView = findViewById(R.id.main_activity_feature_title)

    bind {
        featureTitle.setText(R.string.cuvvaFeature)
    }
}

fun transactionsFeature() = adapterDelegate<MainItem.TransactionsFeature, MainItem>(
    R.layout.main_activity_feature
) {
    val featureTitle: TextView = findViewById(R.id.main_activity_feature_title)

    bind {
        featureTitle.setText(R.string.transactionsFeature)
    }
}

fun rxFeature() = adapterDelegate<MainItem.RxFeature, MainItem>(
    R.layout.main_activity_feature
) {
    val featureTitle: TextView = findViewById(R.id.main_activity_feature_title)

    bind {
        featureTitle.setText(R.string.rxFeature)
    }
}

fun coroutineFeature() = adapterDelegate<MainItem.CoroutineFeature, MainItem>(
    R.layout.main_activity_feature
) {
    val featureTitle: TextView = findViewById(R.id.main_activity_feature_title)

    bind {
        featureTitle.setText(R.string.coroutineFeature)
    }
}