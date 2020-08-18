package com.blockchain.core.network.breakingbad

import com.blockchain.core.network.BaseRepository
import com.blockchain.core.network.breakingbad.datamodel.BreakingBadCharacter

class BreakingBadRepository(private val api: BreakingBadAPI) : BaseRepository() {

    suspend fun getBreakingBadCharacters(): List<BreakingBadCharacter>? {
        return safeApiCall(
            call = { api.getBreakingBadCharactersAsync().await() },
            errorMessage = "Error fetching from BreakingBadAPI"
        )
    }
}