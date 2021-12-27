package com.droidlabs.transaction.ui.transactionsFragmentCoroutine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droidlabs.core.di.IoDispatcher
import com.droidlabs.core.network.transaction.api.BlockchainApiFactory
import com.droidlabs.core.network.transaction.api.BlockchainRepository
import com.droidlabs.core.network.transaction.api.datamodel.BlockchainMultiAddressResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class TransactionViewModel @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + ioDispatcher

    private val scope = CoroutineScope(coroutineContext)

    private val repository: BlockchainRepository =
        BlockchainRepository(BlockchainApiFactory.blockchainAPI)

    val multiAddressLiveData = MutableLiveData<BlockchainMultiAddressResponse>()

    fun fetchMultiAddress(address: String) {
        scope.launch {
            val multiAddress = repository.getMultiAddress(address)
            multiAddressLiveData.postValue(multiAddress)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}