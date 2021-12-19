package com.droidlabs.breakingbad.di

import androidx.lifecycle.ViewModel
import com.droidlabs.breakingbad.ui.fragments.character_details.CharacterDetailsViewModel
import com.droidlabs.breakingbad.ui.fragments.characters.CharactersViewModel
import com.droidlabs.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@InstallIn(SingletonComponent::class)
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
}
