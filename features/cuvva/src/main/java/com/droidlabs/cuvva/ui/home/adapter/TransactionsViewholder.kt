package com.droidlabs.cuvva.ui.home.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import kotlinx.android.synthetic.main.itemview_home.view.*

class TransactionsViewholder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val icon: ImageView = itemView.itemview_home_icon
    val brand: TextView = itemView.itemview_home_brand
    val type: TextView = itemView.itemview_home_type
    val policy_action: Button = itemView.itemview_home_policy_action
    val reg_plate: TextView = itemView.itemview_home_reg_plate
    val total_policies: TextView = itemView.itemview_home_total_policies
    val policy_time_remaining_percent: CircularProgressBar = itemView.itemview_home_policy_time_remaining_percent
    val policy_time_remaining_text: TextView = itemView.itemview_home_policy_time_remaining_text
}