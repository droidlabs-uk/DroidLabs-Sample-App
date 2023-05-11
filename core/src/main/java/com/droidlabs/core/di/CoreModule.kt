package com.droidlabs.core.di

import android.content.Context
import androidx.room.Room
import com.droidlabs.core.network.ExceptionHandler
import com.droidlabs.core.network.ExceptionHandlerImpl
import com.droidlabs.core.network.ktor.KtorClient
import com.droidlabs.core.network.transaction.data.local.BlockchainCache
import com.droidlabs.core.network.transaction.data.local.BlockchainCacheImpl
import com.droidlabs.core.network.transaction.data.local.database.BlockchainDatabase
import com.droidlabs.core.network.transaction.data.remote.api.BlockchainApiFactory
import com.droidlabs.core.network.transaction.domain.model.BLOCKCHAIN_TABLE_NAME
import com.droidlabs.core.network.transaction.domain.repository.BlockchainRepository
import com.droidlabs.core.network.transaction.domain.repository.BlockchainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module(includes = [CoreModule.BindingModule::class])
class CoreModule {

    @Provides
    fun provideBlockchainApi() = BlockchainApiFactory.blockchainApi

    @Provides
    fun provideKtorClient() = KtorClient

    @Provides
    @Singleton
    fun provideBlockchainDatabase(@ApplicationContext appContext: Context): BlockchainDatabase =
        Room.databaseBuilder(
            context = appContext,
            klass = BlockchainDatabase::class.java,
            name = BLOCKCHAIN_TABLE_NAME,
        ).build()

    @Provides
    @Singleton
    fun provideBlockchainDao(database: BlockchainDatabase) = database.blockchainDao()

    @InstallIn(SingletonComponent::class)
    @Module
    internal interface BindingModule {
        @Binds
        @Singleton
        fun bindBlockchainRepository(
            repositoryImpl: BlockchainRepositoryImpl
        ): BlockchainRepository

        @Binds
        @Singleton
        fun bindBlockchainCache(
            blockchainCacheImpl: BlockchainCacheImpl
        ): BlockchainCache

        @Binds
        @Singleton
        fun bindExceptionHandler(
            exceptionHandlerImpl: ExceptionHandlerImpl
        ): ExceptionHandler
    }
}