package com.blockchain.breakingbad.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.blockchain.breakingbad.ui.adapter.delegates.getCharacters
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class DelegatingBreakingBadAdapter(clicks: (BreakingBadActions) -> Unit) :
    AsyncListDifferDelegationAdapter<BreakingBadItem>(
        diffUtil,
        getCharacters(clicks)
    ) {
    companion object {
        private val diffUtil: DiffUtil.ItemCallback<BreakingBadItem> =
            object : DiffUtil.ItemCallback<BreakingBadItem>() {
                override fun areItemsTheSame(
                    oldItem: BreakingBadItem,
                    newItem: BreakingBadItem
                ): Boolean = oldItem == newItem

                override fun areContentsTheSame(
                    oldItem: BreakingBadItem,
                    newItem: BreakingBadItem
                ): Boolean = oldItem == newItem
            }
    }
}

sealed class BreakingBadActions {
    object CharacterClicked : BreakingBadActions()
}

sealed class BreakingBadItem {
    object Character : BreakingBadItem()
}