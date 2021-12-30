package com.droidlabs.core.network.transaction.data.api

import com.droidlabs.core.network.transaction.domain.model.BlockchainMultiAddress
import io.reactivex.Flowable
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BlockchainAPI {
    @GET("multiaddr")
    fun getMultiAddress(@Query("active") active: String): Deferred<Response<BlockchainMultiAddress>>

    @GET("multiaddr")
    fun getMultiAddressRx(@Query("active") active: String): Flowable<Response<BlockchainMultiAddress>>
}