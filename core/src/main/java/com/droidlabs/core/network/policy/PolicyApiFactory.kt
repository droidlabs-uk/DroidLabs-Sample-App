package com.droidlabs.core.network.policy

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val policyBaseUrl = "https://www.mocky.io/"

object PolicyApiFactory {

    private val policyClient = OkHttpClient().newBuilder().build()

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .client(policyClient)
        .baseUrl(policyBaseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val policyAPI : PolicyAPI = retrofit().create(PolicyAPI::class.java)
}