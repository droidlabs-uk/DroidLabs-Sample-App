package com.droidlabs.core.network.transaction.data.api

import com.droidlabs.core.network.ktor.KtorClient
import com.droidlabs.core.network.transaction.domain.model.BlockchainMultiAddress
import io.ktor.client.call.body
import io.ktor.client.request.get

object BlockchainServiceImpl : BlockchainService {
    private val httpClient by lazy { KtorClient.getInstance }

    override suspend fun getMultiAddress(active: String): BlockchainMultiAddress =
        httpClient.get("$blockchainBaseUrl/multiaddr").body()
//            parseQueryString(active) // TODO
}