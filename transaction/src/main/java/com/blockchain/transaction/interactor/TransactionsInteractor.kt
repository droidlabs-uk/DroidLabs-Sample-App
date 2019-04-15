package com.blockchain.transaction.interactor

import com.blockchain.core.network.api.BlockchainAPI
import com.blockchain.core.network.api.datamodel.Txs
import rx.Observable

class TransactionsInteractor(private val blockchainAPI: BlockchainAPI): ITransactionsInteractor {

    override fun getTransactions(address: String): Observable<List<Txs>> =
        blockchainAPI.getMultiAddressRx(address).map { it.body()?.txs }
}

interface ITransactionsInteractor {
    fun getTransactions(address: String): Observable<List<Txs>>
}