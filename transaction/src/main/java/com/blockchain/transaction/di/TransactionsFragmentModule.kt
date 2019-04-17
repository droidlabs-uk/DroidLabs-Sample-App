package com.blockchain.transaction.di

import com.blockchain.core.network.api.ApiFactory
import com.blockchain.transaction.interactor.ITransactionsInteractor
import com.blockchain.transaction.interactor.TransactionsInteractor
import com.blockchain.transaction.ui.transactionsFragmentCoroutine.TransactionsFragmentCoroutine
import com.blockchain.transaction.ui.transactionsFragmentRx.*
import com.blockchain.transaction.ui.transactionsFragmentRx.presenter.DefaultTransactionProcessor
import com.blockchain.transaction.ui.transactionsFragmentRx.presenter.ITransactionPresenter
import com.blockchain.transaction.ui.transactionsFragmentRx.presenter.ITransactionProcessor
import com.blockchain.transaction.ui.transactionsFragmentRx.presenter.TransactionPresenter
import dagger.Module
import dagger.Provides
import rx.Scheduler
import rx.schedulers.Schedulers

@Module
class TransactionsFragmentModule {
    @Provides
    fun provideTransactionsFragmentRx(transactionsFragmentRx: TransactionsFragmentRx): TransactionsFragmentRx = transactionsFragmentRx

    @Provides
    fun provideTransactionsFragmentCoroutine(transactionsFragmentCoroutine: TransactionsFragmentCoroutine): TransactionsFragmentCoroutine = transactionsFragmentCoroutine

    @Provides
    fun transactionPresenter(transactionProcessor: ITransactionProcessor, scheduler: Scheduler): ITransactionPresenter =
        TransactionPresenter(transactionProcessor, scheduler)

    @Provides
    fun provideTransactionsProcessor(interactor: ITransactionsInteractor, scheduler: Scheduler): ITransactionProcessor =
        DefaultTransactionProcessor(interactor, scheduler)

    @Provides
    fun provideTransactionsInteractor(): ITransactionsInteractor = TransactionsInteractor(ApiFactory.blockchainAPI)

    @Provides
    fun provideRxScheduler(): Scheduler =  Schedulers.computation()
}