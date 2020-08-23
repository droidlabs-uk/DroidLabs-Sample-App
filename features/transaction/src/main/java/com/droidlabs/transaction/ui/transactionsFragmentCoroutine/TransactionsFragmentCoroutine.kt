package com.droidlabs.transaction.ui.transactionsFragmentCoroutine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.droidlabs.transaction.R
import com.droidlabs.transaction.ui.transactionsFragmentRx.TransactionsAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_transactions.*


class TransactionsFragmentCoroutine: Fragment() {

    private lateinit var transactionViewModel: TransactionViewModel

    private val transactionsAdapter by lazy { TransactionsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        layoutInflater.inflate(R.layout.fragment_transactions, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTransactionsRecyclerView()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val address = "xpub6CfLQa8fLgtouvLxrb8EtvjbXfoC1yqzH6YbTJw4dP7srt523AhcMV8Uh4K3TWSHz9oDWmn9MuJogzdGU3ncxkBsAC9wFBLmFrWT9Ek81kQ"

        transactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel::class.java)

        transactionViewModel.fetchMultiAddress(address)

        transactionViewModel.multiAddressLiveData.observe(viewLifecycleOwner, Observer {
            transactionsAdapter.transactions = it.txs
        })
    }

    private fun initTransactionsRecyclerView(){
        val linearLayoutManager = LinearLayoutManager(context)

        fragment_transactions_recyclerview.apply {
            adapter = transactionsAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))
        }
    }

    override fun onStop() {
        transactionViewModel.cancelAllRequests()

        super.onStop()
    }
}