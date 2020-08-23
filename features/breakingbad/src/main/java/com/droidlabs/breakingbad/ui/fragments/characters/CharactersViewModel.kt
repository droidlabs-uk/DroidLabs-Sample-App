package com.droidlabs.breakingbad.ui.fragments.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droidlabs.breakingbad.ui.fragments.characters.CharactersViewUIM.*
import com.droidlabs.breakingbad.ui.fragments.toUIM
import com.droidlabs.core.network.breakingbad.BreakingBadApiFactory
import com.droidlabs.core.network.breakingbad.BreakingBadRepository
import com.droidlabs.core.network.breakingbad.datamodel.BreakingBadCharacter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val repository: BreakingBadRepository
) : ViewModel() {
    var charactersLiveData = MutableLiveData<CharactersViewUIM>()

    private val scope = CoroutineScope(Dispatchers.Default)

    fun getBreakingBadCharacters() {
        charactersLiveData.value = LoadingCharactersView

        scope.launch {
            val characters = repository.getBreakingBadCharacters()
            charactersLiveData.postValue(
                when (characters) {
                    null -> ErrorCharactersView
                    else -> SuccessCharactersView(characters)
                }
            )
        }
    }

    fun searchForCharacter(query: String) {
        charactersLiveData.value = LoadingCharactersView

        scope.launch {
            val characters =
                repository.getBreakingBadCharacters()
                    ?.filter { it.name.contains(query, ignoreCase = true) }
            charactersLiveData.postValue(
                when (characters) {
                    null -> ErrorCharactersView
                    else -> SuccessCharactersView(characters)
                }
            )
        }
    }
}

sealed class CharactersViewUIM {
    object LoadingCharactersView : CharactersViewUIM()
    data class SuccessCharactersView(
        val breakingBadCharacters: List<BreakingBadCharacter>
    ) : CharactersViewUIM()

    object ErrorCharactersView : CharactersViewUIM()
}
