package com.blockchain.core.network.policy

import com.blockchain.core.network.BaseRepository
import com.blockchain.core.network.policy.datamodel.PolicyResponse

class PolicyRepository(private val api: PolicyAPI): BaseRepository() {

    suspend fun getPolicy(policy: String): PolicyResponse? {
        return safeApiCall(
            call = { api.getPolicies(policy).await() },
            errorMessage = "Error fetching policy from API"
        )
    }
}