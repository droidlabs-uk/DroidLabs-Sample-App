package com.blockchain.core.network.transaction.api

import com.blockchain.core.network.transaction.api.datamodel.BlockchainMultiAddressResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface BlockchainAPI {
    @GET("multiaddr")
    fun getMultiAddress(@Query("active") active: String): Deferred<Response<BlockchainMultiAddressResponse>>

    @GET("multiaddr")
    fun getMultiAddressRx(@Query("active") active: String): Observable<Response<BlockchainMultiAddressResponse>>
}