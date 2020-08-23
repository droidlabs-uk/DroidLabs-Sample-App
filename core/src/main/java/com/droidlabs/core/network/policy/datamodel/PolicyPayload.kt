package com.droidlabs.core.network.policy.datamodel

data class PolicyPayload(
    val user_id: String,
    val user_revision: String,
    val policy_id: String,
    val original_policy_id: String,
    val reference_code: String,
    val start_date: String,
    val end_date: String,
    val incident_phone: String,
    val vehicle: Vehicle,
    val documents: Documents,
    val pricing: Pricing
)