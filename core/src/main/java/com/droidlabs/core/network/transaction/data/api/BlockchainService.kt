package com.droidlabs.core.network.transaction.data.api

import com.droidlabs.core.network.transaction.domain.model.BlockchainMultiAddress

interface BlockchainService {
    suspend fun getMultiAddress(active: String): BlockchainMultiAddress
}
