package com.droidlabs.breakingbad.ui.fragments.characters

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.droidlabs.breakingbad.R
import com.droidlabs.breakingbad.databinding.FragmentCharactersBinding
import com.droidlabs.breakingbad.ui.adapter.BreakingBadActions
import com.droidlabs.breakingbad.ui.adapter.DelegatingBreakingBadAdapter
import com.droidlabs.breakingbad.ui.fragments.toItems
import com.droidlabs.core.network.breakingbad.datamodel.BreakingBadCharacter
import com.droidlabs.core.utils.GridSpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharactersFragment : Fragment(R.layout.fragment_characters) {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: CharactersViewModel by viewModels(factoryProducer = { factory })

    private lateinit var binding: FragmentCharactersBinding

    private val characterAdapter = DelegatingBreakingBadAdapter() { actions ->
        when (actions) {
            is BreakingBadActions.CharacterClicked -> navigateToDetailsFragment(actions.char_id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCharactersBinding.bind(view)

        initBreakingBadAdapter()

        viewModel.getBreakingBadCharacters()

        viewModel.charactersLiveData.observe(this as LifecycleOwner) {
            setBreakingBadCharacters(it)
        }
    }

    private fun initBreakingBadAdapter() {
        binding.fragmentCharacterRecyclerview.apply {
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
        binding.fragmentCharacterProgressbar.visibility = View.VISIBLE
    }

    private fun setCharacters(breakingBadCharacters: List<BreakingBadCharacter>) {
        binding.fragmentCharacterProgressbar.visibility = View.GONE
        characterAdapter.items = breakingBadCharacters.toItems()
    }

    private fun setError() {
        binding.fragmentCharacterProgressbar.visibility = View.GONE

        Toast.makeText(
            requireContext(),
            "Error loading Characters List",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        menu.clear()
        inflater.inflate(R.menu.menu_bb, menu)

        val searchView: SearchView = menu.findItem(R.id.menu_search)?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchForCharacter(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        searchView.setOnCloseListener {
            viewModel.getBreakingBadCharacters()
            false
        }
    }
}

