package com.droidlabs.core.network.transaction.domain.usecases

import com.droidlabs.core.di.IoDispatcher
import com.droidlabs.core.network.Result
import com.droidlabs.core.network.Result.Error
import com.droidlabs.core.network.Result.Loading
import com.droidlabs.core.network.Result.Success
import com.droidlabs.core.network.transaction.domain.mapper.BlockchainMapper
import com.droidlabs.core.network.transaction.domain.model.Txs
import com.droidlabs.core.network.transaction.domain.repository.BlockchainRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

class BlockchainGetTxsUseCase @Inject constructor(
    private val blockchainRepository: BlockchainRepository,
    private val blockchainMapper: BlockchainMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun fetchTxs(address: String): Flow<Result<List<Txs>>> =
        flow {
            emit(Loading)

            emitAll(blockchainRepository.getMultiAddressKtor(address, force = false).map {
                if (it != null) {
                    Success(blockchainMapper.mapToTxs(it))
                } else {
                    Error(Exception("null"))// TODO
                }
            })
        }.catch {
            emit(Error(it.fillInStackTrace()))
        }.onEach {
            Timber.d(it.toString())
        }.flowOn(ioDispatcher)
}
