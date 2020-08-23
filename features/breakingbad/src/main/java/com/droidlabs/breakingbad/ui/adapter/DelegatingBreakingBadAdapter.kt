package com.droidlabs.breakingbad.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.droidlabs.breakingbad.ui.adapter.delegates.getCharacters
import com.droidlabs.breakingbad.ui.fragments.BreakingBadCharacterUIM
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
    data class CharacterClicked(val char_id: Int) : BreakingBadActions()
}

sealed class BreakingBadItem {
    data class CharacterItem(val character: BreakingBadCharacterUIM) : BreakingBadItem()
}