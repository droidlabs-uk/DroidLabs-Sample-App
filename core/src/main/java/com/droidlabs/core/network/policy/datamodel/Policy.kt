package com.droidlabs.core.network.policy.datamodel

data class Policy(
    val type: String,
    val timestamp: String,
    val unique_key: String,
    val payload: PolicyPayload
)