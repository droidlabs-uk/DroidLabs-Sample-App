package com.droidlabs.app.di

import com.droidlabs.app.navigation.NavigatorImpl
import com.droidlabs.core.navigation.Navigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class NavigatorModule {
    @Binds
    abstract fun bindNavigator(navigatorImpl: NavigatorImpl): Navigator
}