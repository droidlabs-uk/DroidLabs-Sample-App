package com.blockchain.breakingbad.ui.fragments.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.blockchain.breakingbad.R
import com.blockchain.breakingbad.ui.adapter.BreakingBadActions
import com.blockchain.breakingbad.ui.adapter.BreakingBadItem
import com.blockchain.breakingbad.ui.adapter.DelegatingBreakingBadAdapter
import com.blockchain.breakingbad.ui.fragments.toItems
import com.blockchain.core.network.breakingbad.datamodel.BreakingBadCharacter
import com.blockchain.core.utils.GridSpacingItemDecoration
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_characters.*
import javax.inject.Inject

class CharactersFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: CharactersViewModel by viewModels(factoryProducer = { factory })

    private val characterAdapter = DelegatingBreakingBadAdapter() { actions ->
        when (actions) {
            is BreakingBadActions.CharacterClicked -> navigateToDetailsFragment(actions.char_id)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_characters, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBreakingBadAdapter()

        viewModel.getBreakingBadCharacters()

        viewModel.charactersLiveData.observe(
            this as LifecycleOwner,
            Observer {
                setBreakingBadCharacters(it)
            }
        )
    }

    private fun initBreakingBadAdapter() {
        fragment_character_recyclerview.apply {
            adapter = characterAdapter
            layoutManager = GridLayoutManager(activity, 2)
            addItemDecoration(
                GridSpacingItemDecoration(
                    spanCount = 2, spacing = 8, includeEdge = true
                )
            )
        }
    }

    private fun navigateToDetailsFragment(char_id: Int) {
        findNavController().navigate(
            CharactersFragmentDirections
                .actionCharactersFragmentToCharacterDetailsFragment(charId = char_id)
        )
    }

    private fun setBreakingBadCharacters(uim: CharactersViewUIM) {
        when (uim) {
            CharactersViewUIM.LoadingCharactersView -> setLoading()
            is CharactersViewUIM.SuccessCharactersView -> setCharacters(uim.breakingBadCharacters)
            CharactersViewUIM.ErrorCharactersView -> setError()
        }
    }

    private fun setLoading() {
        fragment_character_progressbar.visibility = View.VISIBLE
    }

    private fun setCharacters(breakingBadCharacters: List<BreakingBadCharacter>) {
        fragment_character_progressbar.visibility = View.GONE
        characterAdapter.items = breakingBadCharacters.toItems()
    }

    private fun setError() {
        fragment_character_progressbar.visibility = View.GONE

        Toast.makeText(
            requireContext(),
            "Error loading Characters List",
            Toast.LENGTH_SHORT
        ).show()
    }
}

