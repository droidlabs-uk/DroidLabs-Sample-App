package com.droidlabs.transaction.ui.transactionsFragmentCoroutine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droidlabs.core.network.transaction.api.BlockchainApiFactory
import com.droidlabs.core.network.transaction.api.BlockchainRepository
import com.droidlabs.core.network.transaction.api.datamodel.BlockchainMultiAddressResponse
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TransactionViewModel : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository: BlockchainRepository = BlockchainRepository(BlockchainApiFactory.blockchainAPI)

    val multiAddressLiveData =  MutableLiveData<BlockchainMultiAddressResponse>()

    fun fetchMultiAddress(address: String){
        scope.launch {
            val multiAddress = repository.getMultiAddress(address)
            multiAddressLiveData.postValue(multiAddress)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}