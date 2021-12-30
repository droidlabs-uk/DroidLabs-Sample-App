package com.droidlabs.core.di

import com.droidlabs.core.network.transaction.data.api.BlockchainApiFactory
import com.droidlabs.core.network.transaction.data.api.BlockchainRepository
import com.droidlabs.core.network.transaction.data.api.BlockchainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module(includes = [CoreModule.BindingModule::class])
class CoreModule {

    @Provides
    internal fun provideBlockchainAPI() = BlockchainApiFactory.blockchainAPI

    @InstallIn(SingletonComponent::class)
    @Module
    internal interface BindingModule {
        @Binds
        @Singleton
        abstract fun bindBlockchainRepository(
            repositoryImpl: BlockchainRepositoryImpl
        ): BlockchainRepository
    }
}