package com.blockchain.core.network.policy

import com.blockchain.core.network.policy.datamodel.PolicyResponse
import com.blockchain.core.network.transaction.api.datamodel.BlockchainMultiAddressResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface PolicyAPI {
    @GET("v2")
    fun getPolicies(@Query("policy") policy: String): Deferred<Response<PolicyResponse>>
}