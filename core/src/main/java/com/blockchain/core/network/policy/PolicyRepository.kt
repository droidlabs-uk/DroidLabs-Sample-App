package com.blockchain.core.network.policy

import com.blockchain.core.network.BaseRepository
import com.blockchain.core.network.policy.datamodel.Policy

class PolicyRepository(private val api: PolicyAPI): BaseRepository() {

    suspend fun getPolicy(policy: String): List<Policy>? {
        return safeApiCall(
            call = { api.getPolicies(policy).await() },
            errorMessage = "Error fetching policy from API"
        )
    }
}