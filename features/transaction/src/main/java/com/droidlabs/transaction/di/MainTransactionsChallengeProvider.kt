package com.droidlabs.transaction.di

import com.droidlabs.transaction.di.TransactionsFragmentModule
import com.droidlabs.transaction.ui.transactionsFragmentCoroutine.TransactionsFragmentCoroutine
import com.droidlabs.transaction.ui.transactionsFragmentRx.TransactionsFragmentRx
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentProvider {
//    @ContributesAndroidInjector(modules = [(TransactionsFragmentModule::class)])
//    abstract fun bindTransactionsFragmentRx(): TransactionsFragmentRx
//
//    @ContributesAndroidInjector(modules = [(TransactionsFragmentModule::class)])
//    abstract fun bindTransactionsFragmentCoroutine(): TransactionsFragmentCoroutine
}