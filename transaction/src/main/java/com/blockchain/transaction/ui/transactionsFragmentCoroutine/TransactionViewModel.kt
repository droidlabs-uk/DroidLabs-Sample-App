package com.blockchain.transaction.ui.transactionsFragmentCoroutine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blockchain.core.network.api.ApiFactory
import com.blockchain.core.network.api.BlockchainRepository
import com.blockchain.core.network.api.datamodel.BlockchainMultiAddressResponse
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TransactionViewModel : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository: BlockchainRepository = BlockchainRepository(ApiFactory.blockchainAPI)

    val multiAddressLiveData =  MutableLiveData<BlockchainMultiAddressResponse>()

    fun fetchMultiAddress(address: String){
        scope.launch {
            val multiAddress = repository.getMultiAddress(address)
            multiAddressLiveData.postValue(multiAddress)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}