package com.blockchain.breakingbad.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.blockchain.breakingbad.R
import com.blockchain.breakingbad.ui.adapter.BreakingBadActions
import com.blockchain.breakingbad.ui.adapter.BreakingBadAdapterItemManager
import com.blockchain.breakingbad.ui.adapter.DelegatingBreakingBadAdapter
import com.blockchain.core.utils.GridSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_characters.*


class CharactersFragment : Fragment() {

    private val characterAdapter = DelegatingBreakingBadAdapter() { actions ->
        when (actions) {
            BreakingBadActions.CharacterClicked -> TODO()
        }
    }

    private val breakingBadAdapterItemManager = BreakingBadAdapterItemManager()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_characters, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBreakingBadAdapter()
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

        characterAdapter.items = breakingBadAdapterItemManager.getItems()
    }
}