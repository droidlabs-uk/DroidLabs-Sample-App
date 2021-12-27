package com.droidlabs.transaction.ui.transactionsFragmentRx

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.droidlabs.transaction.ui.compose.ErrorScreen
import com.droidlabs.transaction.ui.compose.LoadingScreen
import com.droidlabs.transaction.ui.compose.TransactionComposeList
import com.droidlabs.transaction.ui.compose.toViewState
import com.droidlabs.transaction.ui.transactionsFragmentRx.events.InitialIntent
import com.droidlabs.transaction.ui.transactionsFragmentRx.events.TransactionIntent
import com.droidlabs.transaction.ui.transactionsFragmentRx.events.TransactionViewState
import com.droidlabs.transaction.ui.transactionsFragmentRx.presenter.ITransactionPresenter
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@AndroidEntryPoint
class TransactionsFragmentRx : Fragment() {

    @Inject
    lateinit var presenter: ITransactionPresenter

    private val disposableBag = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        ComposeView(requireContext()).apply {
            setContent {
                TransactionsFragmentRxView()
            }
        }

    override fun onStart() {
        super.onStart()

        val address =
            "xpub6CfLQa8fLgtouvLxrb8EtvjbXfoC1yqzH6YbTJw4dP7srt523AhcMV8Uh4K3TWSHz9oDWmn9MuJogzdGU3ncxkBsAC9wFBLmFrWT9Ek81kQ"

        viewIntent(address).subscribe(presenter.binder)
    }

    private fun viewIntent(address: String): Flowable<TransactionIntent> =
        Flowable.just(InitialIntent(address))

    override fun onStop() {
        disposableBag.clear()

        super.onStop()
    }

    @Composable
    private fun TransactionsFragmentRxView() {
        val viewState = presenter.state.subscribeAsState(initial = TransactionViewState()).value

        if (viewState.transactionsError.isError) ErrorScreen(errorMessage = viewState.transactionsError.message)

        when {
            viewState.isLoading() -> LoadingScreen()
            else -> TransactionComposeList(itemViewStates = viewState.transactions.toViewState())
        }
    }
}