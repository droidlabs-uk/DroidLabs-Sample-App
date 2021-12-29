package com.droidlabs.core.network.transaction.domain.model

class BlockchainMultiAddress(
    val addresses: List<Address>,
    val wallet: Wallet,
    val txs: List<Txs>,
    val info: Info
)

data class Address(
    val total_received: Int,
    val total_sent: Int,
    val final_balance: Int
)

data class Wallet(
    val final_balance: Int
)

data class Txs(
    val result: Int,
    val time: Int,
    val hash: String,
    val fee: Int
)

data class Info(
    val conversion: Int
)