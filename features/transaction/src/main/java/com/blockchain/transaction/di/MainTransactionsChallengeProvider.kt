package com.blockchain.transaction.di

import com.blockchain.transaction.di.TransactionsFragmentModule
import com.blockchain.transaction.ui.transactionsFragmentCoroutine.TransactionsFragmentCoroutine
import com.blockchain.transaction.ui.transactionsFragmentRx.TransactionsFragmentRx
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentProvider {
    @ContributesAndroidInjector(modules = [(TransactionsFragmentModule::class)])
    abstract fun bindTransactionsFragmentRx(): TransactionsFragmentRx

    @ContributesAndroidInjector(modules = [(TransactionsFragmentModule::class)])
    abstract fun bindTransactionsFragmentCoroutine(): TransactionsFragmentCoroutine
}