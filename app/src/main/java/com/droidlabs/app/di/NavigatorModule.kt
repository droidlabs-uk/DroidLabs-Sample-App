package com.droidlabs.transactionsChallenge.di

import com.droidlabs.core.navigation.Navigator
import com.droidlabs.transactionsChallenge.navigation.NavigatorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class NavigatorModule {
    @Binds
    abstract fun bindNavigator(navigatorImpl: NavigatorImpl): Navigator
}