package com.droidlabs.transaction.ui.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.droidlabs.core.network.transaction.api.datamodel.Txs
import com.droidlabs.transaction.R
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

@Composable
fun CentredCircularProgressIndicator() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator(modifier = Modifier.size(50.dp))
    }
}

fun List<Txs>.toViewState() =
    map {
        TransactionItemViewState(
            text = it.hash,
            time = it.time,
            amount = it.result
        )
    }