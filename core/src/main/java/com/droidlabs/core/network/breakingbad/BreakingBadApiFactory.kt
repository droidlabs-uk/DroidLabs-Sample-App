package com.droidlabs.core.network.breakingbad

import com.droidlabs.core.network.policy.PolicyApiFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

private const val breakingBadBaseUrl = "https://breakingbadapi.com/api/"

object BreakingBadApiFactory {

    private val breakingBadClient = OkHttpClient().newBuilder().build()

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .client(BreakingBadApiFactory.breakingBadClient)
        .baseUrl(breakingBadBaseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val breakingBadAPI: BreakingBadAPI = retrofit().create(BreakingBadAPI::class.java)
}