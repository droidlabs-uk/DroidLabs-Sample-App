package com.droidlabs.breakingbad.ui.fragments.character_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.droidlabs.breakingbad.R
import com.droidlabs.breakingbad.ui.fragments.character_details.CharactersDetailsViewUIM.*
import com.droidlabs.core.network.breakingbad.datamodel.BreakingBadCharacter
import com.squareup.picasso.Picasso
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

        viewModel.getBreakingBadCharacterDetails(args.charId)

        viewModel.characterDetailsLiveData.observe(
            this as LifecycleOwner,
            Observer {
                setBreakingBadCharacterDetails(it)
            }
        )
    }

    private fun setBreakingBadCharacterDetails(
        uim: CharactersDetailsViewUIM
    ) {
        when (uim) {
            LoadingCharactersDetailsView -> showLoading()
            is SuccessCharactersDetailsView -> showCharacter(uim.breakingBadCharacter)
            ErrorCharactersDetailsView -> showError()
        }
    }

    private fun showLoading() {
        fragment_character_details_container.visibility = View.GONE
        fragment_character_details_progressbar.visibility = View.VISIBLE
    }

    private fun showCharacter(character: BreakingBadCharacter) {
        fragment_character_details_progressbar.visibility = View.GONE
        fragment_character_details_container.visibility = View.VISIBLE

        character.apply {
            Picasso
                .get()
                .load(img)
                .resize(500, 500)
                .into(fragment_character_details_image)

            fragment_character_details_name.text = name
            fragment_character_details_nickname.text = nickname
            fragment_character_details_occupation_text.text = occupation.joinToString()
            fragment_character_details_status_text.text = status
            fragment_character_details_season_appearance_text.text = appearance.joinToString()
        }
    }

    private fun showError() {
        fragment_character_details_progressbar.visibility = View.GONE
        fragment_character_details_container.visibility = View.VISIBLE

        Toast.makeText(
            requireContext(),
            "Error loading Character Details",
            Toast.LENGTH_SHORT
        ).show()
    }
}
