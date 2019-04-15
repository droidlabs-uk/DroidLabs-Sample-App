package com.blockchain.transactionsChallenge.di

import com.blockchain.transaction.di.TransactionsFragmentModule
import com.blockchain.transaction.ui.transactionsFragmentCoroutine.TransactionsFragmentCoroutine
import com.blockchain.transaction.ui.transactionsFragmentRx.TransactionsFragmentRx
import com.blockchain.transactionsChallenge.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityProvider {
    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindMainActivity(): MainActivity
}

@Module
abstract class MainFragmentProvider {
    @ContributesAndroidInjector(modules = [(TransactionsFragmentModule::class)])
    abstract fun bindTransactionsFragmentRx(): TransactionsFragmentRx

    @ContributesAndroidInjector(modules = [(TransactionsFragmentModule::class)])
    abstract fun bindTransactionsFragmentCoroutine(): TransactionsFragmentCoroutine
}