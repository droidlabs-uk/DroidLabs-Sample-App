package com.blockchain.main.di

import com.blockchain.main.MainActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityProvider {
    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity
}
