package com.blockchain.transactionsChallenge

import android.app.Activity
import android.app.Application
import com.blockchain.transactionsChallenge.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TransactionsChallengeApplication: Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
            .inject(this)

        super.onCreate()
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector
}