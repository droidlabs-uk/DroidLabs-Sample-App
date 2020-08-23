package com.droidlabs.cuvva

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droidlabs.core.network.policy.PolicyApiFactory
import com.droidlabs.core.network.policy.PolicyRepository
import com.droidlabs.core.network.policy.datamodel.Policy
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PolicyViewModel : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository: PolicyRepository = PolicyRepository(PolicyApiFactory.policyAPI)

    val policyLiveData =  MutableLiveData<List<Policy>>()

    fun fetchPolicy(policy: String){
        scope.launch {
            val policy = repository.getPolicy(policy)
            policyLiveData.postValue(policy)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}