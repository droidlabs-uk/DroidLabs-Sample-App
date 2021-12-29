package com.droidlabs.transaction.interactor

import com.droidlabs.core.network.transaction.data.api.BlockchainAPI
import com.droidlabs.core.network.transaction.domain.model.Txs
import io.reactivex.Flowable

class TransactionsInteractor(private val blockchainAPI: BlockchainAPI): ITransactionsInteractor {

    override fun getTransactions(address: String): Flowable<List<Txs>> =
        blockchainAPI.getMultiAddressRx(address).map { it.body()?.txs }
}

interface ITransactionsInteractor {
    fun getTransactions(address: String): Flowable<List<Txs>>
}