package com.droidlabs.breakingbad.ui.fragments.character_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droidlabs.breakingbad.ui.fragments.character_details.CharactersDetailsViewUIM.*
import com.droidlabs.core.network.breakingbad.BreakingBadApiFactory
import com.droidlabs.core.network.breakingbad.BreakingBadRepository
import com.droidlabs.core.network.breakingbad.datamodel.BreakingBadCharacter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailsViewModel @Inject constructor(
    private val repository: BreakingBadRepository
) : ViewModel() {
    var characterDetailsLiveData = MutableLiveData<CharactersDetailsViewUIM>()

    fun getBreakingBadCharacterDetails(char_id: Int) {
        characterDetailsLiveData.value = LoadingCharactersDetailsView

        CoroutineScope(Dispatchers.Default).launch {
            val characterList = repository.getBreakingBadCharacters()

            val character = characterList?.first { it.char_id == char_id }

            characterDetailsLiveData.postValue(
                when (character) {
                    null -> ErrorCharactersDetailsView
                    else -> SuccessCharactersDetailsView(character)
                }
            )
        }
    }
}

sealed class CharactersDetailsViewUIM {
    object LoadingCharactersDetailsView : CharactersDetailsViewUIM()
    data class SuccessCharactersDetailsView(val breakingBadCharacter: BreakingBadCharacter) :
        CharactersDetailsViewUIM()

    object ErrorCharactersDetailsView : CharactersDetailsViewUIM()
}