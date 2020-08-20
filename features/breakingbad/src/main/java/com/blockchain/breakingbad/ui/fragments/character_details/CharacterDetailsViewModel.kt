package com.blockchain.breakingbad.ui.fragments.character_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blockchain.breakingbad.ui.fragments.character_details.CharactersDetailsViewUIM.*
import com.blockchain.core.network.breakingbad.BreakingBadApiFactory
import com.blockchain.core.network.breakingbad.BreakingBadRepository
import com.blockchain.core.network.breakingbad.datamodel.BreakingBadCharacter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailsViewModel @Inject constructor() : ViewModel() {
    var characterDetailsLiveData = MutableLiveData<CharactersDetailsViewUIM>()

    private val repository: BreakingBadRepository =
        BreakingBadRepository(BreakingBadApiFactory.breakingBadAPI)

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