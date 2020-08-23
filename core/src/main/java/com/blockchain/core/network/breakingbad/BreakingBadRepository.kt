package com.blockchain.core.network.breakingbad

import com.blockchain.core.network.BaseRepository
import com.blockchain.core.network.breakingbad.datamodel.BreakingBadCharacter
import javax.inject.Inject

class BreakingBadRepositoryImpl @Inject constructor(private val api: BreakingBadAPI) :
    BreakingBadRepository() {

    override suspend fun getBreakingBadCharacters(): List<BreakingBadCharacter>? {
        return safeApiCall(
            call = { api.getBreakingBadCharactersAsync().await() },
            errorMessage = "Error fetching from BreakingBadAPI"
        )
    }
}

abstract class BreakingBadRepository : BaseRepository() {
    abstract suspend fun getBreakingBadCharacters(): List<BreakingBadCharacter>?
}