package com.droidlabs.core.network.policy.datamodel

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