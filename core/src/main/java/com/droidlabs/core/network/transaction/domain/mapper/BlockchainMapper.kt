package com.droidlabs.core.network.transaction.domain.mapper

import com.droidlabs.core.network.transaction.domain.model.BlockchainMultiAddress
import com.droidlabs.core.network.transaction.domain.model.Txs
import javax.inject.Inject

class BlockchainMapper @Inject constructor() {
    fun mapToTxs(blockchainMultiAddress: BlockchainMultiAddress) = blockchainMultiAddress.txs
}