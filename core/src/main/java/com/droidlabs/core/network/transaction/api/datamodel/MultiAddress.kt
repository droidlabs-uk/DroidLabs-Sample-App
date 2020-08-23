package com.droidlabs.core.network.transaction.api.datamodel

class BlockchainMultiAddressResponse (
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

data class Info(
    val conversion: Int
)