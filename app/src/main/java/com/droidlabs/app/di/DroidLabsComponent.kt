package com.droidlabs.transactionsChallenge.di

import android.app.Application
import android.content.Context
import com.droidlabs.breakingbad.di.BreakingBadModule
import com.droidlabs.core.network.breakingbad.di.BreakingBadRepositoryModule
import com.droidlabs.main.di.MainActivityProvider
import com.droidlabs.transaction.di.MainFragmentProvider
import com.droidlabs.transactionsChallenge.TransactionsChallengeApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjection
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (AndroidInjectionModule::class),
        (NavigatorModule::class),
        (ViewModelModule::class),
        (MainActivityProvider::class),
//        (MainFragmentProvider::class),
        (BreakingBadModule::class),
        (BreakingBadRepositoryModule::class)
    ]
)

internal interface ApplicationComponent : AndroidInjector<TransactionsChallengeApplication> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<TransactionsChallengeApplication>
}