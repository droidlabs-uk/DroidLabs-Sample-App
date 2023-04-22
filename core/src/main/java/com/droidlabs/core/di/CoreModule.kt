package com.droidlabs.core.di

import android.content.Context
import androidx.room.Room
import com.droidlabs.core.network.transaction.data.api.BlockchainApiFactory
import com.droidlabs.core.network.transaction.data.api.BlockchainRepository
import com.droidlabs.core.network.transaction.data.api.BlockchainRepositoryImpl
import com.droidlabs.core.network.transaction.data.database.BlockchainDatabase
import com.droidlabs.core.network.transaction.domain.model.BLOCKCHAIN_TABLE_NAME
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
    internal fun provideBlockchainAPI() = BlockchainApiFactory.blockchainAPI

    @Provides
    @Singleton
    fun provideBlockchainDatabase(@ApplicationContext appContext: Context) =
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
        abstract fun bindBlockchainRepository(
            repositoryImpl: BlockchainRepositoryImpl
        ): BlockchainRepository
    }
}