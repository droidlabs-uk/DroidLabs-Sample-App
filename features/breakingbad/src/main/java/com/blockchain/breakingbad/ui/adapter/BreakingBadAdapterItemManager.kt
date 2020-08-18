package com.blockchain.breakingbad.ui.adapter

class BreakingBadAdapterItemManager {
    fun getItems(): List<BreakingBadItem>? {

        val visibleItems = mutableListOf<BreakingBadItem>()

        visibleItems += BreakingBadItem.Character

        return visibleItems
    }
}