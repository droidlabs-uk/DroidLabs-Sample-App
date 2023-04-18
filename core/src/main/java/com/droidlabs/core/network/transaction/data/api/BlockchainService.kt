package com.droidlabs.core.network.transaction.data.api

import com.droidlabs.core.network.transaction.domain.model.BlockchainMultiAddress
import retrofit2.http.GET
import retrofit2.http.Query

interface BlockchainService {

    @GET("multiaddr")
    suspend fun getMultiAddress(@Query("active") active: String): BlockchainMultiAddress
}