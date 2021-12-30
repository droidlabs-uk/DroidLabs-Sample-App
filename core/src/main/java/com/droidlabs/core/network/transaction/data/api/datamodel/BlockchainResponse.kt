package com.droidlabs.core.network.transaction.data.api.datamodel

import com.droidlabs.core.network.transaction.domain.model.BlockchainMultiAddress

data class BlockchainResponse (
    val results: List<BlockchainMultiAddress>
)