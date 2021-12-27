package com.droidlabs.transaction.ui.transactionsFragmentCoroutine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.droidlabs.transaction.ui.compose.TransactionComposeList
import com.droidlabs.transaction.ui.compose.toViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionsFragmentCoroutine : Fragment() {

    private val transactionViewModel: TransactionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        ComposeView(requireContext()).apply {
            setContent {
                TransactionsFragmentCoroutineView(transactionViewModel)
            }
        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val address =
            "xpub6CfLQa8fLgtouvLxrb8EtvjbXfoC1yqzH6YbTJw4dP7srt523AhcMV8Uh4K3TWSHz9oDWmn9MuJogzdGU3ncxkBsAC9wFBLmFrWT9Ek81kQ"

        transactionViewModel.fetchMultiAddress(address)
    }

    override fun onStop() {
        transactionViewModel.cancelAllRequests()

        super.onStop()
    }

    @Composable
    private fun TransactionsFragmentCoroutineView(
        transactionViewModel: TransactionViewModel
    ) {
        val transactions by transactionViewModel.multiAddressLiveData.observeAsState()

        transactions?.txs?.let {
            when {
                it.isEmpty() -> TransactionComposeList(itemViewStates = listOf())
                else -> TransactionComposeList(itemViewStates = it.toViewState())
            }
        }
    }
}
