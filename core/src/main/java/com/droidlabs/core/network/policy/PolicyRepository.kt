package com.droidlabs.core.network.policy

import com.droidlabs.core.network.BaseRepository
import com.droidlabs.core.network.policy.datamodel.Policy

class PolicyRepository(private val api: PolicyAPI): BaseRepository() {

    suspend fun getPolicy(policy: String): List<Policy>? {
        return safeApiCall(
            call = { api.getPolicies(policy).await() },
            errorMessage = "Error fetching policy from API"
        )
    }
}