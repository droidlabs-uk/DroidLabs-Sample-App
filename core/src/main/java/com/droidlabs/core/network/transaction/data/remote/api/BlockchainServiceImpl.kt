package com.droidlabs.core.network.transaction.data.remote.api

import com.droidlabs.core.network.ktor.KtorClient
import com.droidlabs.core.network.transaction.domain.model.BlockchainMultiAddress
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class BlockchainServiceImpl @Inject constructor(private val ktorClient: KtorClient) : BlockchainService {

    override suspend fun getMultiAddress(active: String): BlockchainMultiAddress =
        ktorClient.getInstance.get("$blockchainBaseUrl/multiaddr") {
            url { parameters.append("active", active) }
        }.body()
}
