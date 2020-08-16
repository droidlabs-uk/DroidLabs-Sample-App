package com.blockchain.transactionsChallenge.di

import com.blockchain.core.navigation.Navigator
import com.blockchain.transactionsChallenge.navigation.NavigatorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class NavigatorModule {
    @Binds
    abstract fun bindNavigator(navigatorImpl: NavigatorImpl): Navigator
}