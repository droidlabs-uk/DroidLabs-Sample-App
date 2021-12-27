package com.droidlabs.cuvva.ui.home.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.droidlabs.cuvva.databinding.ItemviewCuvvaBinding
import com.mikhaellopez.circularprogressbar.CircularProgressBar

class TransactionsViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemviewCuvvaBinding.bind(itemView)

    val icon: ImageView = binding.itemviewHomeIcon
    val brand: TextView = binding.itemviewHomeBrand
    val type: TextView = binding.itemviewHomeType
    val policyAction: Button = binding.itemviewHomePolicyAction
    val regPlate: TextView = binding.itemviewHomeRegPlate
    val totalPolicies: TextView = binding.itemviewHomeTotalPolicies
    val policyTimeRemainingPercent: CircularProgressBar = binding.itemviewHomePolicyTimeRemainingPercent
    val policyTimeRemainingText: TextView = binding.itemviewHomePolicyTimeRemainingText
}