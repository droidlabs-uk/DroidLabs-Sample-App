package com.droidlabs.core.network.transaction.domain.usecases

import com.droidlabs.core.di.IoDispatcher
import com.droidlabs.core.network.Result
import com.droidlabs.core.network.Result.Success
import com.droidlabs.core.network.transaction.data.api.BlockchainRepository
import com.droidlabs.core.network.transaction.domain.mapper.BlockchainMapper
import com.droidlabs.core.network.transaction.domain.model.Txs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

class BlockchainGetTxsUseCase @Inject constructor(
    private val blockchainRepository: BlockchainRepository,
    private val blockchainMapper: BlockchainMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun fetchTxs(address: String): Flow<Result<List<Txs>>> =
        flow {
            emit(Result.Loading)

            emitAll(blockchainRepository.getMultiAddress(address).map {
                Success(blockchainMapper.mapToTxs(it))
            })
        }.catch {
            emit(Result.Error(it))
        }.onEach {
            Timber.d(it.toString())
        }.flowOn(ioDispatcher)
}