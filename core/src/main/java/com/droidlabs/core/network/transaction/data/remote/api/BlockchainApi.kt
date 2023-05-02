package com.droidlabs.core.network.transaction.data.remote.api

import com.droidlabs.core.network.transaction.domain.model.BlockchainMultiAddress
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BlockchainApi {
    @GET("multiaddr")
    fun getMultiAddressAsync(@Query("active") active: String): BlockchainMultiAddress

    @GET("multiaddr")
    fun getMultiAddressRx(@Query("active") active: String): Flowable<Response<BlockchainMultiAddress>>
}
