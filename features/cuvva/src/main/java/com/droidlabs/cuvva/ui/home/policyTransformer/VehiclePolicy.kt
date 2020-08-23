package com.droidlabs.cuvva.ui.home.policyTransformer

data class VehiclePolicy(
    val active: Boolean,
    val icon: Int,
    val make: String,
    val type: String,
    val policyAction: String,
    val pretty_reg_plate: String,
    val total_policies: Int,
    val remaining_time_percent: Int,
    val remaining_time: String
)