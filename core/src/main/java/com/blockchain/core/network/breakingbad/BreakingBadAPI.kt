package com.blockchain.core.network.breakingbad

import com.blockchain.core.network.breakingbad.datamodel.BreakingBadCharacter
import com.blockchain.core.network.policy.datamodel.Policy
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BreakingBadAPI {
    @GET("characters")
    fun getBreakingBadCharactersAsync(): Deferred<Response<List<BreakingBadCharacter>>>
}