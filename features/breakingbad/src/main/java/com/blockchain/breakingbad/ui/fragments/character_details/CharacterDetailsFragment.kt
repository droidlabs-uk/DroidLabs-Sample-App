package com.blockchain.breakingbad.ui.fragments.character_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.blockchain.breakingbad.R
import com.blockchain.core.network.breakingbad.datamodel.BreakingBadCharacter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_characters_details.*
import javax.inject.Inject

class CharacterDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: CharacterDetailsViewModel by viewModels(factoryProducer = { factory })

    private val args: CharacterDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_characters_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getBreakingBadCharacterDetails()

        viewModel.characterDetailsLiveData.observe(
            this as LifecycleOwner,
            Observer {
                setBreakingBadCharacterDetails(it, args.position)
            }
        )
    }

    private fun setBreakingBadCharacterDetails(
        characterDetails: List<BreakingBadCharacter>,
        position: Int
    ) {

        val character = characterDetails.filter { it.char_id == position }[0]

        fragment_character_details_name.text = character.name
        fragment_character_details_nickname.text = character.nickname
        fragment_character_details_occupation_text.text = character.occupation.toString()
        fragment_character_details_status_text.text = character.status
        fragment_character_details_season_appearance_text.text = character.appearance.toString()

    }
}
