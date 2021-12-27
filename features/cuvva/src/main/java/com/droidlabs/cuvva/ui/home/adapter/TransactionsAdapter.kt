package com.droidlabs.cuvva.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.droidlabs.cuvva.R
import com.droidlabs.cuvva.ui.home.policyTransformer.VehiclePolicy

class TransactionsAdapter: RecyclerView.Adapter<TransactionsViewholder>() {
    var vehiclePolicies: List<VehiclePolicy> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TransactionsViewholder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.itemview_cuvva,
                parent,
                false
            )
        )

    override fun getItemCount() = vehiclePolicies.size

    override fun onBindViewHolder(holder: TransactionsViewholder, position: Int) {
        val vehiclePolicy = vehiclePolicies[position]

        holder.icon.setImageResource(vehiclePolicy.icon)
        holder.brand.text = vehiclePolicy.make

        holder.type.text = vehiclePolicy.type
        holder.regPlate.text = setRegPlate(vehiclePolicy.pretty_reg_plate)
        holder.policyAction.text = vehiclePolicy.policyAction
        holder.totalPolicies.text = setTotalPolicies(vehiclePolicy.total_policies)
        holder.policyTimeRemainingPercent.progress = vehiclePolicy.remaining_time_percent.toFloat()
        holder.policyTimeRemainingText.text = vehiclePolicy.remaining_time
    }

    private fun setTotalPolicies(policies: Int) = "Total policies\n$policies"

    private fun setRegPlate(regPlate: String) = "Reg Plate\n$regPlate"
}