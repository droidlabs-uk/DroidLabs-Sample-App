package com.blockchain.breakingbad.ui.fragments.character_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blockchain.breakingbad.R
import com.blockchain.breakingbad.ui.adapter.BreakingBadItem
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CharacterDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: CharacterDetailsViewModel by viewModels(factoryProducer = { factory })

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
                setBreakingBadCharacterDetails(it)
            }
        )
    }

    private fun setBreakingBadCharacterDetails(characterDetails: List<BreakingBadItem>) {

    }
}
