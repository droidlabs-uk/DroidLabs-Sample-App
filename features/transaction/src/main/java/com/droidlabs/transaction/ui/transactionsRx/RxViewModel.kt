package com.droidlabs.transaction.ui.transactionsRx

import androidx.lifecycle.ViewModel
import com.droidlabs.transaction.ui.transactionsRx.events.InitialIntent
import com.droidlabs.transaction.ui.transactionsRx.events.TransactionIntent
import com.droidlabs.transaction.ui.transactionsRx.presenter.ITransactionPresenter
import com.droidlabs.transaction.utils.address
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import javax.inject.Inject

@HiltViewModel
class RxViewModel @Inject constructor(
    private val presenter: ITransactionPresenter
) : ViewModel() {

    init {
        viewIntent(address).subscribe(presenter.binder)
    }

    val viewState = presenter.state

    private fun viewIntent(address: String): Flowable<TransactionIntent> =
        Flowable.just(InitialIntent(address))
}
