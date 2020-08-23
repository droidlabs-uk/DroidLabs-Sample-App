package com.droidlabs.transaction.di

import com.droidlabs.core.network.transaction.api.BlockchainApiFactory
import com.droidlabs.transaction.interactor.ITransactionsInteractor
import com.droidlabs.transaction.interactor.TransactionsInteractor
import com.droidlabs.transaction.ui.transactionsFragmentCoroutine.TransactionsFragmentCoroutine
import com.droidlabs.transaction.ui.transactionsFragmentRx.TransactionsFragmentRx
import com.droidlabs.transaction.ui.transactionsFragmentRx.presenter.DefaultTransactionProcessor
import com.droidlabs.transaction.ui.transactionsFragmentRx.presenter.ITransactionPresenter
import com.droidlabs.transaction.ui.transactionsFragmentRx.presenter.ITransactionProcessor
import com.droidlabs.transaction.ui.transactionsFragmentRx.presenter.TransactionPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

@Module
class TransactionsFragmentModule {
//    @Provides
//    fun provideTransactionsFragmentRx(transactionsFragmentRx: TransactionsFragmentRx): TransactionsFragmentRx = transactionsFragmentRx
//
//    @Provides
//    fun provideTransactionsFragmentCoroutine(transactionsFragmentCoroutine: TransactionsFragmentCoroutine): TransactionsFragmentCoroutine = transactionsFragmentCoroutine
//
//    @Provides
//    fun transactionPresenter(transactionProcessor: ITransactionProcessor, scheduler: Scheduler): ITransactionPresenter =
//        TransactionPresenter(transactionProcessor, scheduler)
//
//    @Provides
//    fun provideTransactionsProcessor(interactor: ITransactionsInteractor, scheduler: Scheduler): ITransactionProcessor =
//        DefaultTransactionProcessor(interactor, scheduler)
//
//    @Provides
//    fun provideTransactionsInteractor(): ITransactionsInteractor = TransactionsInteractor(BlockchainApiFactory.blockchainAPI)
//
//    @Provides
//    fun provideRxScheduler(): Scheduler =  Schedulers.computation()
}