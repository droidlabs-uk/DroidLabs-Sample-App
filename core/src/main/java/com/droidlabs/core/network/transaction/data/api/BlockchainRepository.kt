package com.droidlabs.core.network.transaction.data.api

import com.droidlabs.core.network.BaseRepository
import com.droidlabs.core.network.transaction.domain.model.BlockchainMultiAddress
import com.droidlabs.core.network.transaction.domain.model.Info
import com.droidlabs.core.network.transaction.domain.model.Wallet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class BlockchainRepositoryImpl @Inject constructor(private val api: BlockchainAPI) :
    BlockchainRepository() {

    //TODO: only here for dealing with nullability of safeApiCall - needs revisiting
    private val defaultBlockchainAddress =
        BlockchainMultiAddress(
            addresses = listOf(),
            wallet = Wallet(0),
            txs = listOf(),
            info = Info(0)
        )

    override suspend fun getMultiAddress(address: String): Flow<BlockchainMultiAddress> =
        flowOf(
            safeApiCall(
                call = { api.getMultiAddress(address).await() },
                errorMessage = "Error fetching multiAddress from API"
            ) ?: defaultBlockchainAddress
        )

    // TODO: should replace above function
    override suspend fun getMultiAddressKtor(address: String): Flow<BlockchainMultiAddress> =
        flowOf(BlockchainServiceImpl.getMultiAddress(address))
}

abstract class BlockchainRepository : BaseRepository() {
    abstract suspend fun getMultiAddress(address: String): Flow<BlockchainMultiAddress>
    abstract suspend fun getMultiAddressKtor(address: String): Flow<BlockchainMultiAddress>
}