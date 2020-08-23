package com.droidlabs.core.network.transaction.api

import com.droidlabs.core.network.transaction.api.datamodel.BlockchainMultiAddressResponse
import io.reactivex.Flowable
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BlockchainAPI {
    @GET("multiaddr")
    fun getMultiAddress(@Query("active") active: String): Deferred<Response<BlockchainMultiAddressResponse>>

    @GET("multiaddr")
    fun getMultiAddressRx(@Query("active") active: String): Flowable<Response<BlockchainMultiAddressResponse>>
}