package com.droidlabs.transaction.di

import com.droidlabs.core.network.transaction.data.remote.api.BlockchainApiFactory
import com.droidlabs.transaction.interactor.ITransactionsInteractor
import com.droidlabs.transaction.interactor.TransactionsInteractor
import com.droidlabs.transaction.ui.transactionsRx.presenter.DefaultTransactionProcessor
import com.droidlabs.transaction.ui.transactionsRx.presenter.ITransactionPresenter
import com.droidlabs.transaction.ui.transactionsRx.presenter.ITransactionProcessor
import com.droidlabs.transaction.ui.transactionsRx.presenter.TransactionPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

@InstallIn(SingletonComponent::class)
@Module
class TransactionModule {

    @Provides
    fun transactionPresenter(
            transactionProcessor: ITransactionProcessor,
            scheduler: Scheduler
    ): ITransactionPresenter =
            TransactionPresenter(transactionProcessor, scheduler)

    @Provides
    fun provideTransactionsProcessor(
            interactor: ITransactionsInteractor,
            scheduler: Scheduler
    ): ITransactionProcessor =
            DefaultTransactionProcessor(interactor, scheduler)

    @Provides
    fun provideTransactionsInteractor(): ITransactionsInteractor =
            TransactionsInteractor(BlockchainApiFactory.blockchainApi)

    @Provides
    fun provideRxScheduler(): Scheduler = Schedulers.computation()
}