package com.droidlabs.transaction.ui.transactionsCoroutine

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidlabs.core.di.IoDispatcher
import com.droidlabs.core.network.Result
import com.droidlabs.core.network.transaction.domain.model.Txs
import com.droidlabs.core.network.transaction.domain.usecases.BlockchainGetTxsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val blockchainGetTxsUseCase: BlockchainGetTxsUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    // TODO: hardcoded for simplification
    private val address =
        "xpub6CfLQa8fLgtouvLxrb8EtvjbXfoC1yqzH6YbTJw4dP7srt523AhcMV8Uh4K3TWSHz9oDWmn9MuJogzdGU3ncxkBsAC9wFBLmFrWT9Ek81kQ"

    private val refreshState = mutableStateOf(false)

    //TODO: does not trigger init() and get past Loading
    val getTransactionsFlow: StateFlow<Result<List<Txs>>> = flow<Result<List<Txs>>> {
        blockchainGetTxsUseCase.fetchTxs(address)
    }.flowOn(ioDispatcher).stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        Result.Loading
    )
}