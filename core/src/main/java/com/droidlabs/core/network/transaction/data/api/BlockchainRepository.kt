package com.droidlabs.core.network.transaction.data.api

import com.droidlabs.core.network.BaseRepository
import com.droidlabs.core.network.transaction.domain.model.BlockchainMultiAddress
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class BlockchainRepositoryImpl @Inject constructor() :
    BlockchainRepository() {

    override suspend fun getMultiAddressKtor(address: String): Flow<BlockchainMultiAddress> =
        flowOf(BlockchainServiceImpl.getMultiAddress(address))
}

abstract class BlockchainRepository : BaseRepository() {
    abstract suspend fun getMultiAddressKtor(address: String): Flow<BlockchainMultiAddress>
}
