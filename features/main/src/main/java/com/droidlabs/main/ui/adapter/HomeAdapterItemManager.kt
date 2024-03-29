package com.droidlabs.main.ui.adapter

class HomeAdapterItemManager {
    fun getItems(): List<MainItem> {

        val visibleItems = mutableListOf<MainItem>()

        visibleItems += MainItem.CuvvaFeature
        visibleItems += MainItem.TransactionsFeature
        visibleItems += MainItem.BreakingBadFeature

        return visibleItems
    }
}
