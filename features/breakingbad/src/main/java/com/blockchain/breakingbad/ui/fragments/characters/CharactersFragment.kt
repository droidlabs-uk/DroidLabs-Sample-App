package com.blockchain.breakingbad.ui.fragments.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.blockchain.breakingbad.R
import com.blockchain.breakingbad.ui.adapter.BreakingBadActions
import com.blockchain.breakingbad.ui.adapter.DelegatingBreakingBadAdapter
import com.blockchain.core.utils.GridSpacingItemDecoration
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_characters.*

class CharactersFragment : DaggerFragment() {

//    @Inject
//    lateinit var factory: ViewModelProvider.Factory

//    private val viewModel: CharactersViewModel by viewModels(factoryProducer = { factory })

    private val characterAdapter = DelegatingBreakingBadAdapter() { actions ->
        when (actions) {
            BreakingBadActions.CharacterClicked -> TODO()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_characters, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBreakingBadAdapter()

//        viewModel.getBreakingBadCharacters()
//
//        viewModel.charactersLiveData.observe(
//            this as LifecycleOwner,
//            Observer {
//                characterAdapter.items = it
//            }
//        )
    }

    private fun initBreakingBadAdapter() {
        fragment_character_recyclerview.apply {
            adapter = characterAdapter
            layoutManager = GridLayoutManager(activity, 2)
            addItemDecoration(
                GridSpacingItemDecoration(
                    spanCount = 2, spacing = 8, includeEdge = false
                )
            )
        }
    }
}