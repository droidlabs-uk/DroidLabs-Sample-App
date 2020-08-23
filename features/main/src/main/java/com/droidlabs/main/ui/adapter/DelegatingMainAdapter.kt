package com.droidlabs.main.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.droidlabs.main.ui.adapter.delegates.*
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class DelegatingMainAdapter(
    clicks: (MainActions) -> Unit
) : AsyncListDifferDelegationAdapter<MainItem>(
    diffUtil,
    cuvvaFeature(clicks),
    transactionsFeature(clicks),
    rxFeature(clicks),
    coroutineFeature(clicks),
    breakingBadFeature(clicks)
) {
    companion object {
        private val diffUtil: DiffUtil.ItemCallback<MainItem> =
            object : DiffUtil.ItemCallback<MainItem>() {
                override fun areItemsTheSame(
                    oldItem: MainItem,
                    newItem: MainItem
                ): Boolean = oldItem == newItem

                override fun areContentsTheSame(
                    oldItem: MainItem,
                    newItem: MainItem
                ): Boolean = oldItem == newItem
            }
    }
}

sealed class MainActions {
    object CuvvaFeatureClicked : MainActions()
    object TransactionsFeatureClicked : MainActions()
    object RxFeatureClicked : MainActions()
    object CoroutineFeatureClicked : MainActions()
    object BreakingBadFeatureClicked : MainActions()
}

sealed class MainItem {
    object CuvvaFeature : MainItem()
    object TransactionsFeature : MainItem()
    object RxFeature : MainItem()
    object CoroutineFeature : MainItem()
    object BreakingBadFeature : MainItem()
}