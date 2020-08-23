package com.droidlabs.core.network.transaction.api.datamodel

data class Txs (
    val result: Int,
    val time: Int,
    val hash: String,
    val fee: Int
)