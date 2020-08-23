package com.droidlabs.core.network.policy

import com.droidlabs.core.network.policy.datamodel.Policy
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PolicyAPI {
    @GET("v2/{policy_id}")
    fun getPolicies(@Path(value = "policy_id", encoded = true) policy: String): Deferred<Response<List<Policy>>>
}