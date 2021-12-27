package com.droidlabs.breakingbad.ui.fragments.character_details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.droidlabs.breakingbad.R
import com.droidlabs.breakingbad.databinding.FragmentCharactersDetailsBinding
import com.droidlabs.breakingbad.ui.fragments.character_details.CharactersDetailsViewUIM.*
import com.droidlabs.core.network.breakingbad.datamodel.BreakingBadCharacter
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment(R.layout.fragment_characters_details) {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: CharacterDetailsViewModel by viewModels(factoryProducer = { factory })

    private lateinit var binding: FragmentCharactersDetailsBinding

    private val args: CharacterDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCharactersDetailsBinding.bind(view)

        viewModel.getBreakingBadCharacterDetails(args.charId)

        viewModel.characterDetailsLiveData.observe(this as LifecycleOwner) {
            setBreakingBadCharacterDetails(it)
        }
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
        binding.fragmentCharacterDetailsContainer.visibility = View.GONE
        binding.fragmentCharacterDetailsProgressbar.visibility = View.VISIBLE
    }

    private fun showCharacter(character: BreakingBadCharacter) {
        binding.fragmentCharacterDetailsProgressbar.visibility = View.GONE
        binding.fragmentCharacterDetailsContainer.visibility = View.VISIBLE

        character.apply {
            Picasso
                .get()
                .load(img)
                .resize(500, 500)
                .into(binding.fragmentCharacterDetailsImage)

            binding.fragmentCharacterDetailsName.text = name
            binding.fragmentCharacterDetailsNickname.text = nickname
            binding.fragmentCharacterDetailsOccupation.text = occupation.joinToString()
            binding.fragmentCharacterDetailsStatusText.text = status
            binding.fragmentCharacterDetailsSeasonAppearanceText.text = appearance.joinToString()
        }
    }

    private fun showError() {
        binding.fragmentCharacterDetailsProgressbar.visibility = View.GONE
        binding.fragmentCharacterDetailsContainer.visibility = View.VISIBLE

        Toast.makeText(
            requireContext(),
            "Error loading Character Details",
            Toast.LENGTH_SHORT
        ).show()
    }
}
