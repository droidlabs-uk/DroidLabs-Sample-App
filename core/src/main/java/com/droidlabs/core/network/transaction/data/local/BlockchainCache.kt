package com.droidlabs.core.network.transaction.data.local

import com.droidlabs.core.network.transaction.domain.model.BlockchainMultiAddress

interface BlockchainCache {
    suspend fun getBlockchainMultiAddressById(id: Long): BlockchainMultiAddress?
    suspend fun getBlockchainMultiAddressByAddress(address: String): BlockchainMultiAddress?
    suspend fun storeBlockchainMultiAddress(blockchainMultiAddress: BlockchainMultiAddress)
    suspend fun deleteAllBlockchainMultiAddress()
}
