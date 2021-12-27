package com.droidlabs.core.network.breakingbad.di

import com.droidlabs.core.network.breakingbad.BreakingBadApiFactory
import com.droidlabs.core.network.breakingbad.BreakingBadRepository
import com.droidlabs.core.network.breakingbad.BreakingBadRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [BreakingBadRepositoryModule.BindingModule::class])
class BreakingBadRepositoryModule {

    @Provides
    internal fun provideBreakingBadAPI() = BreakingBadApiFactory.breakingBadAPI

    @InstallIn(SingletonComponent::class)
    @Module
    internal interface BindingModule {
        @Binds
        @Singleton
        abstract fun bindBreakingBadRepository(
            repositoryImpl: BreakingBadRepositoryImpl
        ): BreakingBadRepository
    }
}