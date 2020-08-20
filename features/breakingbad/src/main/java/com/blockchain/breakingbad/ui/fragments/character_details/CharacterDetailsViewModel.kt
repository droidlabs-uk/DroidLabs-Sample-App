package com.blockchain.breakingbad.ui.fragments.character_details

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blockchain.breakingbad.ui.adapter.BreakingBadItem
import com.blockchain.breakingbad.ui.fragments.toUIM
import com.blockchain.core.network.breakingbad.BreakingBadApiFactory
import com.blockchain.core.network.breakingbad.BreakingBadRepository
import com.blockchain.core.network.breakingbad.datamodel.BreakingBadCharacter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CharacterDetailsViewModel @Inject constructor() : ViewModel() {
    var characterDetailsLiveData = MutableLiveData<List<BreakingBadCharacter>>()

    private val repository: BreakingBadRepository =
        BreakingBadRepository(BreakingBadApiFactory.breakingBadAPI)

    fun getBreakingBadCharacterDetails() {
        CoroutineScope(Dispatchers.Default).launch {
            val characters = repository.getBreakingBadCharacters()
            characterDetailsLiveData.postValue(characters)
        }
    }
}
