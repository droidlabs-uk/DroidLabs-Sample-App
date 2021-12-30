package com.droidlabs.app.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(
        factory: DroidLabsViewModelFactory
    ): ViewModelProvider.Factory
}
