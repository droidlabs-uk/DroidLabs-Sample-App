package com.droidlabs.core.network.transaction.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlockchainMultiAddress(
    @SerialName("addresses")
    val addresses: List<Address>,

    @SerialName("wallet")
    val wallet: Wallet,

    @SerialName("txs")
    val txs: List<Txs>,

    @SerialName("info")
    val info: Info
)

@Serializable
data class Address(
    @SerialName("total_received")
    val total_received: Int,

    @SerialName("total_sent")
    val total_sent: Int,

    @SerialName("final_balance")
    val final_balance: Int
)

@Serializable
data class Wallet(
    @SerialName("final_balance")
    val final_balance: Int
)

@Serializable
data class Txs(
    @SerialName("result")
    val result: Int,

    @SerialName("time")
    val time: Int,

    @SerialName("hash")
    val hash: String,

    @SerialName("fee")
    val fee: Int
)

@Serializable
data class Info(
    @SerialName("conversion")
    val conversion: String
)