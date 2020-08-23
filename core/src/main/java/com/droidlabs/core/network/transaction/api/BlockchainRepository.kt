package com.droidlabs.core.network.transaction.api

import com.droidlabs.core.network.BaseRepository
import com.droidlabs.core.network.transaction.api.datamodel.BlockchainMultiAddressResponse

class BlockchainRepository(private val api: BlockchainAPI): BaseRepository() {

    suspend fun getMultiAddress(address: String): BlockchainMultiAddressResponse? {
        return safeApiCall(
            call = { api.getMultiAddress(address).await() },
            errorMessage = "Error fetching multiAddress from API"
        )
    }
}