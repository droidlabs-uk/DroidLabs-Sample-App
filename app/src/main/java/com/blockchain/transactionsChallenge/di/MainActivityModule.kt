package com.blockchain.transactionsChallenge.di

import com.blockchain.transactionsChallenge.MainActivity
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule{

    @Provides
    fun provideMainActivity(mainActivity: MainActivity): MainActivity = mainActivity
}