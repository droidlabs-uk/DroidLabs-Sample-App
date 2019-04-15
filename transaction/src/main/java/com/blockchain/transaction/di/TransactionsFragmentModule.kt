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

@Module
class TransactionsFragmentModule {
    @Provides
    fun provideTransactionsFragmentRx(transactionsFragmentRx: TransactionsFragmentRx): TransactionsFragmentRx = transactionsFragmentRx

    @Provides
    fun provideTransactionsFragmentCoroutine(transactionsFragmentCoroutine: TransactionsFragmentCoroutine): TransactionsFragmentCoroutine = transactionsFragmentCoroutine

    @Provides
    fun transactionPresenter(transactionProcessor: ITransactionProcessor): ITransactionPresenter =
        TransactionPresenter(transactionProcessor)

    @Provides
    fun provideTransactionsProcessor(interactor: ITransactionsInteractor): ITransactionProcessor =
        DefaultTransactionProcessor(interactor)

    @Provides
    fun provideTransactionsInteractor(): ITransactionsInteractor = TransactionsInteractor(ApiFactory.blockchainAPI)
}