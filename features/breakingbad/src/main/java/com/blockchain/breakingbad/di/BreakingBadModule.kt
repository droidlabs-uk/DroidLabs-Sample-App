package com.blockchain.breakingbad.di

import androidx.lifecycle.ViewModel
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

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeCharactersFragment(): CharactersFragment
}