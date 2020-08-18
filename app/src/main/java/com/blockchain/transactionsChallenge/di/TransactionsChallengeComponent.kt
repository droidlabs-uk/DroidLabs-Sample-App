package com.blockchain.transactionsChallenge.di

import android.app.Application
import com.blockchain.breakingbad.di.BreakingBadModule
import com.blockchain.main.di.MainActivityProvider
import com.blockchain.transaction.di.MainFragmentProvider
import com.blockchain.transactionsChallenge.TransactionsChallengeApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (AndroidInjectionModule::class),
        (NavigatorModule::class),
        (MainActivityProvider::class),
        (MainFragmentProvider::class),
        (BreakingBadModule::class)
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: TransactionsChallengeApplication)
}