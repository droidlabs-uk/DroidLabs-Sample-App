package com.blockchain.transaction.ui.transactionsFragmentRx

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.blockchain.transaction.R
import com.blockchain.transaction.ui.transactionsFragmentRx.events.InitialIntent
import com.blockchain.transaction.ui.transactionsFragmentRx.events.TransactionIntent
import com.blockchain.transaction.ui.transactionsFragmentRx.events.TransactionViewState
import com.blockchain.transaction.ui.transactionsFragmentRx.presenter.ITransactionPresenter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_transactions.*
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class TransactionsFragmentRx: Fragment() {

    @Inject
    lateinit var presenter: ITransactionPresenter

    private val disposableBag = mutableListOf<Subscription>()

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

    override fun onStart() {
        super.onStart()

        val address = "xpub6CfLQa8fLgtouvLxrb8EtvjbXfoC1yqzH6YbTJw4dP7srt523AhcMV8Uh4K3TWSHz9oDWmn9MuJogzdGU3ncxkBsAC9wFBLmFrWT9Ek81kQ"

        presenter.state
            .observeOn(AndroidSchedulers.mainThread())
            .retry()
            .subscribe(this::render, this::streamError)
            .also { disposableBag.add(it) }

        viewIntents(address)
            .subscribe(presenter.binder)
            .also { disposableBag.add(it) }
    }

    private fun initTransactionsRecyclerView(){
        val linearLayoutManager = LinearLayoutManager(context)

        fragment_transactions_recyclerview.apply {
            adapter = transactionsAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))
        }
    }

    private fun viewIntents(address: String): Observable<TransactionIntent> =
        Observable.create { it.onNext(InitialIntent(address)) }


    private fun render(viewState: TransactionViewState) {
        when {
            viewState.isLoading() -> {
                fragment_transactions_recyclerview.visibility = View.GONE
                fragment_transactions_progressbar.visibility = View.VISIBLE
            }

            else -> {
                fragment_transactions_progressbar.visibility = View.GONE
                fragment_transactions_recyclerview.visibility = View.VISIBLE
                transactionsAdapter.transactions = viewState.transactions
            }
        }
    }

    private fun streamError(error: Throwable) =
        Toast.makeText(context, "TransactionsFragmentRx: $error", Toast.LENGTH_LONG).show()

    override fun onStop() {
        disposableBag
            .filter { it.isUnsubscribed.not() }
            .forEach { it.unsubscribe() }

        disposableBag.clear()

        super.onStop()
    }
}