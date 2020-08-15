package com.blockchain.transaction.interactor

import com.blockchain.core.network.transaction.api.BlockchainAPI
import com.blockchain.core.network.transaction.api.datamodel.Txs
import io.reactivex.Flowable

class TransactionsInteractor(private val blockchainAPI: BlockchainAPI): ITransactionsInteractor {

    override fun getTransactions(address: String): Flowable<List<Txs>> =
        blockchainAPI.getMultiAddressRx(address).map { it.body()?.txs }
}

interface ITransactionsInteractor {
    fun getTransactions(address: String): Flowable<List<Txs>>
}