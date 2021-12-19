package com.droidlabs.transaction.ui.transactionsFragmentCoroutine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.droidlabs.core.network.transaction.api.datamodel.Txs
import com.droidlabs.transaction.R
import com.droidlabs.transaction.ui.transactionsFragmentRx.TransactionsAdapter
import com.droidlabs.transaction.utils.convertSatoshiToBTC
import com.droidlabs.transaction.utils.isNegative
import com.droidlabs.transaction.utils.prettyDateString


class TransactionsFragmentCoroutine : Fragment() {

    private lateinit var transactionViewModel: TransactionViewModel

    private val transactionsAdapter by lazy { TransactionsAdapter() }

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

        transactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel::class.java)

        transactionViewModel.fetchMultiAddress(address)
    }

    override fun onStop() {
        transactionViewModel.cancelAllRequests()

        super.onStop()
    }

    //TODO: better decoupling and file layout

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


    data class TransactionItemViewState(
        val text: String,
        val time: Int,
        val amount: Int
    )

    @Composable
    fun TransactionComposeList(
        itemViewStates: List<TransactionItemViewState>
    ) {
        LazyColumn {
            items(itemViewStates) { data ->
                TransactionSimpleListItem(data)
            }
        }
    }

    @Composable
    fun TransactionSimpleListItem(itemViewState: TransactionItemViewState) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dp(50f)),
            border = BorderStroke(Dp(1f), Color.Gray),
            elevation = Dp(0f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = Dp(8f), end = Dp(8f)),
            ) {
                Text(
                    text = prettyDateString(itemViewState.time.toLong()),
                    modifier = Modifier
                        .align(CenterVertically)
                        .weight(2f)
                )

                Text(
                    text = "${convertSatoshiToBTC(itemViewState.amount)} BTC",
                    modifier = Modifier.align(CenterVertically)
                )

                Image(
                    painter = setTransactionDirection(itemViewState.amount.isNegative()),
                    contentDescription = "",
                    alignment = Alignment.CenterEnd,
                    modifier = Modifier
                        .height(Dp(40f))
                        .width(Dp(40f))
                )
            }
        }
    }

    @Composable
    fun setTransactionDirection(outgoingTransaction: Boolean): Painter =
        when (outgoingTransaction) {
            true -> painterResource(id = R.drawable.ic_outgoing_24px)
            else -> painterResource(id = R.drawable.ic_incoming_24px)
        }
}

private fun List<Txs>.toViewState() =
    map {
        TransactionsFragmentCoroutine.TransactionItemViewState(
            text = it.hash,
            time = it.time,
            amount = it.result
        )
    }
