package com.droidlabs.core.network.transaction.data.local

import com.droidlabs.core.network.Cache
import com.droidlabs.core.network.ExceptionHandler
import com.droidlabs.core.network.transaction.domain.model.BlockchainMultiAddress
import timber.log.Timber
import javax.inject.Inject

class BlockchainCacheImpl @Inject constructor(
    private val blockchainDao: BlockchainDao,
    exceptionHandler: ExceptionHandler
) : Cache(exceptionHandler), BlockchainCache {

    override suspend fun getBlockchainMultiAddressById(id: Long) =
        runQuery {
            Timber.d(tag, "Get BlockchainMultiAddress $id")
            blockchainDao.getBlockchainMultiAddressById(id)
        }

    override suspend fun getBlockchainMultiAddressByAddress(address: String) =
        runQuery {
            Timber.d(tag, "Get BlockchainMultiAddress $address")
            blockchainDao.getBlockchainMultiAddressByAddress(address)
        }

    override suspend fun storeBlockchainMultiAddress(blockchainMultiAddress: BlockchainMultiAddress) {
        runQuery {
            Timber.d(tag, "Add BlockchainMultiAddress ${blockchainMultiAddress.id}")
            blockchainDao.insertBlockchainMultiAddress(blockchainMultiAddress)
        }
    }

    override suspend fun deleteAllBlockchainMultiAddress() {
        runQuery {
            Timber.d(tag, "Delete All BlockchainMultiAddresses")
            blockchainDao.deleteAll()
        }
    }

    companion object {
        private const val tag = "LOCAL-SOURCE"
    }
}
