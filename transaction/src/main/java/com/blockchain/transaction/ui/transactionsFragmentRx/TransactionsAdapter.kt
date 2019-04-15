package com.blockchain.transaction.ui.transactionsFragmentRx

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.blockchain.core.network.api.datamodel.Txs
import com.blockchain.transaction.R
import kotlinx.android.synthetic.main.itemview_transaction.view.*
import java.text.SimpleDateFormat
import java.util.*

class TransactionsAdapter : RecyclerView.Adapter<TransactionsViewholder>() {
    var transactions: List<Txs> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionsViewholder =
        TransactionsViewholder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.itemview_transaction,
                parent,
                false
            )
        )

    override fun getItemCount() = transactions.size

    override fun onBindViewHolder(holder: TransactionsViewholder, position: Int) {
        val transaction = transactions[position]

        holder.transactionTime.text = setTransactionTime(transaction.time.toLong())
        holder.transactionAmount.text = setAmount(transaction.result)
        holder.transactionDirection.background = setTransactionDirection(holder.itemView.resources, transaction.result.isNegative() )

    }

    private fun setTransactionDirection(resources: Resources, outgoingTransaction: Boolean): Drawable? {
        return if (outgoingTransaction) {
            resources.getDrawable(R.drawable.ic_outgoing_24px)
        } else {
            resources.getDrawable(R.drawable.ic_incoming_24px).apply { setTint(Color.GREEN) }
        }
    }

    private fun setTransactionTime(time: Long): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.UK)
        return formatter.format(Date(time))
    }

    private fun setAmount(result: Int): String {
        return "$result BTC"
    }
}

fun Int.isNegative() = this < 0

class TransactionsViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val transactionTime: TextView = itemView.itemview_transaction_time
    val transactionAmount: TextView = itemView.itemview_transaction_amount
    val transactionDirection: AppCompatImageView = itemView.itemview_transaction_indicator
}