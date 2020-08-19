package com.blockchain.main.di

import com.blockchain.main.ui.HomeFragment
import com.blockchain.main.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityProvider {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment
}
