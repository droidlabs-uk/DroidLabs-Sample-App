package com.blockchain.breakingbad.ui.fragments.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blockchain.breakingbad.ui.fragments.characters.CharactersViewUIM.*
import com.blockchain.breakingbad.ui.fragments.toUIM
import com.blockchain.core.network.breakingbad.BreakingBadApiFactory
import com.blockchain.core.network.breakingbad.BreakingBadRepository
import com.blockchain.core.network.breakingbad.datamodel.BreakingBadCharacter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel @Inject constructor() : ViewModel() {
    var charactersLiveData = MutableLiveData<CharactersViewUIM>()

    private val scope = CoroutineScope(Dispatchers.Default)

    private val repository: BreakingBadRepository =
        BreakingBadRepository(BreakingBadApiFactory.breakingBadAPI)

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
}

sealed class CharactersViewUIM {
    object LoadingCharactersView : CharactersViewUIM()
    data class SuccessCharactersView(
        val breakingBadCharacters: List<BreakingBadCharacter>
    ) : CharactersViewUIM()
    object ErrorCharactersView : CharactersViewUIM()
}
