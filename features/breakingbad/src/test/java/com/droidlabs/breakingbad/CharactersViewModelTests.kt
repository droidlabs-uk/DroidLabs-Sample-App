package com.droidlabs.breakingbad

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.droidlabs.breakingbad.ui.fragments.characters.CharactersViewUIM
import androidx.lifecycle.Observer
import com.droidlabs.breakingbad.ui.fragments.characters.CharactersViewModel
import com.droidlabs.core.network.breakingbad.BreakingBadRepository
import com.droidlabs.core.network.breakingbad.datamodel.BreakingBadCharacter
import com.droidlabs.core.utils.captureValues
import io.mockk.*
import io.reactivex.Single
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

class CharactersViewModelTests {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val charactersObserver: Observer<CharactersViewUIM> = mockk()
    private val mockRepo = mockk<BreakingBadRepository>()

    private lateinit var viewModel: CharactersViewModel
    private val breakingBadCharacters = listOf<BreakingBadCharacter>()

    @Before
    fun setup() {
        viewModel = CharactersViewModel(mockRepo)
    }

    @Test
    fun `When Repo returns BreakingBadCharacters -- Then ViewModel emits BreakingBadCharacters`() {
        runBlocking {
            coEvery {
                mockRepo.getBreakingBadCharacters()
            } returns breakingBadCharacters

            viewModel.getBreakingBadCharacters()

            viewModel.charactersLiveData.captureValues {
                assertSendsValues(CharactersViewUIM.SuccessCharactersView(breakingBadCharacters))
                coVerify { mockRepo.getBreakingBadCharacters() }
            }
        }
    }
}