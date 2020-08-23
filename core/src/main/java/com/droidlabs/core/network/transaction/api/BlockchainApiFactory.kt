package com.droidlabs.core.network.transaction.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

private const val blockchainBaseUrl = "https://blockchain.info/"

object BlockchainApiFactory {

    private val blockchainClient = OkHttpClient().newBuilder().build()

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .client(blockchainClient)
        .baseUrl(blockchainBaseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    val blockchainAPI : BlockchainAPI = retrofit().create(BlockchainAPI::class.java)
}