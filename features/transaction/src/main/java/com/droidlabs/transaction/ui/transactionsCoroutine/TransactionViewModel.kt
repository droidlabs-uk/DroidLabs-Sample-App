package com.droidlabs.transaction.ui.transactionsCoroutine

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidlabs.core.di.IoDispatcher
import com.droidlabs.core.network.Result
import com.droidlabs.core.network.transaction.domain.model.Txs
import com.droidlabs.core.network.transaction.domain.usecases.BlockchainGetTxsUseCase
import com.droidlabs.transaction.utils.address
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val blockchainGetTxsUseCase: BlockchainGetTxsUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val refreshState = mutableStateOf(false)

    val getTransactionsFlow: StateFlow<Result<List<Txs>>> =
        flow<Result<List<Txs>>> {
            blockchainGetTxsUseCase.fetchTxs(address).collect { emit(it) }
        }.flowOn(ioDispatcher).stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            Result.Loading
        )
}