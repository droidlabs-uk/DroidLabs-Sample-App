package com.droidlabs.transaction.ui.transactionsFragmentRx

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.droidlabs.core.network.transaction.api.datamodel.Txs
import com.droidlabs.transaction.R
import kotlinx.android.synthetic.main.itemview_transaction.view.*
import java.math.BigDecimal
import java.math.RoundingMode
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

        holder.transactionTime.text = prettyDateString(transaction.time.toLong())
        holder.transactionAmount.text = setAmount(transaction.result)
        holder.transactionDirection.background = setTransactionDirection(holder.itemView.context, transaction.result.isNegative() )
    }

    private fun setTransactionDirection(context: Context, outgoingTransaction: Boolean): Drawable? {
        return if (outgoingTransaction) {
            ContextCompat.getDrawable(context, R.drawable.ic_outgoing_24px)
        } else {
            ContextCompat.getDrawable(context, R.drawable.ic_incoming_24px)?.apply { setTint(Color.GREEN) }
        }
    }

    private fun setAmount(result: Int) = "${convertSatoshiToBTC(result)} BTC"
}

fun prettyDateString(milliseconds: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy\nHH:mm:ss z", Locale.UK).apply { timeZone = TimeZone.getTimeZone("UTC") }
    return formatter.format(Date(milliseconds))
}

fun convertSatoshiToBTC(satoshi: Int): BigDecimal =
    BigDecimal(satoshi.toDouble() / 100000000).setScale(8, RoundingMode.HALF_EVEN)

fun Int.isNegative() = this < 0

class TransactionsViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val transactionTime: TextView = itemView.itemview_transaction_time
    val transactionAmount: TextView = itemView.itemview_transaction_amount
    val transactionDirection: AppCompatImageView = itemView.itemview_transaction_indicator
}