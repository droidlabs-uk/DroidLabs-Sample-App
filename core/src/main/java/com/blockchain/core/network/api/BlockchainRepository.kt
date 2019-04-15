package com.blockchain.core.network.api

import com.blockchain.core.network.api.datamodel.BlockchainMultiAddressResponse

class BlockchainRepository(private val api: BlockchainAPI): BaseRepository() {

    suspend fun getMultiAddress(address: String): BlockchainMultiAddressResponse? {
        return safeApiCall(
            call = { api.getMultiAddress(address).await() },
            errorMessage = "Error fetching multiAddress from API"
        )
    }
}