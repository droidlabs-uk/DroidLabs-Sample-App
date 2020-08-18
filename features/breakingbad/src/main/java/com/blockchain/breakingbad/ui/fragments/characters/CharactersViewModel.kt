package com.blockchain.breakingbad.ui.fragments.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.blockchain.breakingbad.ui.adapter.BreakingBadItem
import com.blockchain.core.network.breakingbad.BreakingBadApiFactory
import com.blockchain.core.network.breakingbad.BreakingBadRepository
import com.blockchain.core.network.breakingbad.datamodel.BreakingBadCharacter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CharactersViewModel @Inject constructor() : ViewModel() {
    var charactersLiveData = MutableLiveData<List<BreakingBadItem>>()

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository: BreakingBadRepository =
        BreakingBadRepository(BreakingBadApiFactory.breakingBadAPI)

    fun getBreakingBadCharacters() {
        scope.launch {
            val characters = repository.getBreakingBadCharacters()
            charactersLiveData.postValue(characters.toUIM())
        }
    }
}

private fun List<BreakingBadCharacter>?.toUIM() = this?.map { it.toUIM() }

private fun BreakingBadCharacter.toUIM() = BreakingBadItem.CharacterItem(
    BreakingBadCharacter(
        name = name,
        imageUrl = img
    )
)


data class BreakingBadCharacter(val name: String, val imageUrl: String)