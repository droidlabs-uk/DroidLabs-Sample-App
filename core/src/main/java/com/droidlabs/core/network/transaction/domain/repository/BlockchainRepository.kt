package com.droidlabs.core.network.transaction.domain.repository

import com.droidlabs.core.network.BaseRepository
import com.droidlabs.core.network.transaction.domain.model.BlockchainMultiAddress
import kotlinx.coroutines.flow.Flow

abstract class BlockchainRepository : BaseRepository() {
    abstract suspend fun getMultiAddressKtor(address: String,  force: Boolean): Flow<BlockchainMultiAddress?>
}
