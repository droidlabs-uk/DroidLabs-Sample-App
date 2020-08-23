package com.blockchain.breakingbad.di

import androidx.lifecycle.ViewModel
import com.blockchain.breakingbad.ui.fragments.character_details.CharacterDetailsFragment
import com.blockchain.breakingbad.ui.fragments.character_details.CharacterDetailsViewModel
import com.blockchain.breakingbad.ui.fragments.characters.CharactersFragment
import com.blockchain.breakingbad.ui.fragments.characters.CharactersViewModel
import com.blockchain.core.di.FragmentScoped
import com.blockchain.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class BreakingBadModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    internal abstract fun bindCharactersViewModel(viewModel: CharactersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharacterDetailsViewModel::class)
    internal abstract fun bindCharacterDetailsViewModel(viewModel: CharacterDetailsViewModel): ViewModel

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeCharactersFragment(): CharactersFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeCharacterDetailsFragment(): CharacterDetailsFragment
}
