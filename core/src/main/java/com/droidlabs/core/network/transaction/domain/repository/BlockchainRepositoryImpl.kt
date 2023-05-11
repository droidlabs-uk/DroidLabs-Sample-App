package com.droidlabs.core.network.transaction.domain.repository

import com.droidlabs.core.network.ResourceGroup
import com.droidlabs.core.network.transaction.data.local.BlockchainCache
import com.droidlabs.core.network.transaction.data.remote.api.BlockchainServiceImpl
import com.droidlabs.core.network.transaction.domain.model.BlockchainMultiAddress
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BlockchainRepositoryImpl @Inject constructor(
    private val blockchainCache: BlockchainCache,
    private val blockchainService: BlockchainServiceImpl //TODO: should be interface instead of impl?
) : BlockchainRepository() {

    private val resourceGroup = ResourceGroup<Unit, String, BlockchainMultiAddress>(
        remoteGroupFetch = { TODO() },
        localGroupFetch = { TODO() },
        localGroupStore = { TODO() },
        remoteFetch = { address, _ -> blockchainService.getMultiAddress(address) },
        localFetch = { address, _ -> blockchainCache.getBlockchainMultiAddressByAddress(address) },
        localStore = blockchainCache::storeBlockchainMultiAddress,
        localDelete = blockchainCache::deleteAllBlockchainMultiAddress,
    )

    override suspend fun getMultiAddressKtor(address: String, force: Boolean): Flow<BlockchainMultiAddress?> =
        resourceGroup.queryByKey(address, Unit, force)
}
