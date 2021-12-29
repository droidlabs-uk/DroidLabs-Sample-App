package com.droidlabs.transaction.ui.compose

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.droidlabs.core.network.transaction.api.datamodel.Txs
import com.droidlabs.transaction.R
import com.droidlabs.transaction.ui.transactionsFragmentCoroutine.TransactionViewModel
import com.droidlabs.transaction.utils.convertSatoshiToBTC
import com.droidlabs.transaction.utils.isNegative
import com.droidlabs.transaction.utils.prettyDateString


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
            .height(50.dp),
        border = BorderStroke(1.dp, Color.Gray),
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
        ) {
            Text(
                text = prettyDateString(itemViewState.time.toLong()),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(2f)
            )

            Text(
                text = "${convertSatoshiToBTC(itemViewState.amount)} BTC",
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Image(
                painter = setTransactionDirection(itemViewState.amount.isNegative()),
                contentDescription = "",
                alignment = Alignment.CenterEnd,
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
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

fun List<Txs>.toViewState() =
    map {
        TransactionItemViewState(
            text = it.hash,
            time = it.time,
            amount = it.result
        )
    }


@Composable
fun TransactionsFragmentCoroutineView() {
    val viewModel = hiltViewModel<TransactionViewModel>()

    viewModel.fetchMultiAddress("xpub6CfLQa8fLgtouvLxrb8EtvjbXfoC1yqzH6YbTJw4dP7srt523AhcMV8Uh4K3TWSHz9oDWmn9MuJogzdGU3ncxkBsAC9wFBLmFrWT9Ek81kQ")

    val transactions by viewModel.multiAddressLiveData.observeAsState()

    transactions?.txs?.let {
        when {
            it.isEmpty() -> TransactionComposeList(itemViewStates = listOf(TransactionItemViewState("Empty", 0 , 0)))
            else -> TransactionComposeList(itemViewStates = it.toViewState())
        }
    }
}