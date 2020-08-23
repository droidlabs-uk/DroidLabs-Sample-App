package com.droidlabs.breakingbad.ui.fragments.characters

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.droidlabs.breakingbad.R
import com.droidlabs.breakingbad.ui.adapter.BreakingBadActions
import com.droidlabs.breakingbad.ui.adapter.BreakingBadItem
import com.droidlabs.breakingbad.ui.adapter.DelegatingBreakingBadAdapter
import com.droidlabs.breakingbad.ui.fragments.toItems
import com.droidlabs.core.network.breakingbad.datamodel.BreakingBadCharacter
import com.droidlabs.core.utils.GridSpacingItemDecoration
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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

