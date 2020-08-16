package com.blockchain.main.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.blockchain.main.ui.adapter.delegates.coroutineFeature
import com.blockchain.main.ui.adapter.delegates.cuvvaFeature
import com.blockchain.main.ui.adapter.delegates.rxFeature
import com.blockchain.main.ui.adapter.delegates.transactionsFeature
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class DelegatingMainAdapter() : AsyncListDifferDelegationAdapter<MainItem>(
    diffUtil,
    cuvvaFeature(),
    transactionsFeature(),
    rxFeature(),
    coroutineFeature()
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
    //TODO add onClickListener
}

sealed class MainItem {
    object CuvvaFeature : MainItem()
    object TransactionsFeature : MainItem()
    object RxFeature : MainItem()
    object CoroutineFeature : MainItem()
}