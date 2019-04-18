package com.blockchain.core.network.policy.datamodel

data class Policy(
    val type: String,
    val timestamp: String,
    val unique_key: String,
    val payLoad: PolicyPayload
)

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

data class Vehicle (
    val vrm: String,
    val prettyVrm: String,
    val make: String,
    val model: String,
    val variant: String,
    val color: String
)

data class Documents(
    val certificate_url: String,
    val terms_url: String
)

data class Pricing(
    val underwriter_premium: Int,
    val commission: Int,
    val total_premium: Int,
    val ipt: Int,
    val ipt_rate: Int,
    val extra_fees: Int,
    val vat: Int,
    val deductions: Int,
    val total_payable: Int
)