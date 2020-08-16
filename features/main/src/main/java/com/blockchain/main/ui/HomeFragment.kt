package com.blockchain.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.blockchain.core.navigation.NavigationDest
import com.blockchain.core.navigation.Navigator
import com.blockchain.main.R
import com.blockchain.main.ui.adapter.DelegatingMainAdapter
import com.blockchain.main.ui.adapter.HomeAdapterItemManager
import com.blockchain.main.ui.adapter.MainActions
import com.blockchain.main.utils.GridSpacingItemDecoration
import dagger.android.DaggerFragment
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject


class HomeFragment : dagger.android.support.DaggerFragment() {

    @Inject
    lateinit var navigator: Navigator

    private lateinit var viewModel: HomeViewModel
    private val homeAdapter = DelegatingMainAdapter() { actions ->
        when (actions) {
            MainActions.CuvvaFeatureClicked -> TODO()
            MainActions.TransactionsFeatureClicked -> TODO()
            MainActions.RxFeatureClicked -> TODO()
            MainActions.CoroutineFeatureClicked -> TODO()
            MainActions.BreakingBadFeatureClicked -> navigator.navigateTo(
                NavigationDest.BreakingBad,
                requireContext()
            )
        }
    }

    private val homeAdapterItemManager = HomeAdapterItemManager()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initHomeRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun initHomeRecyclerView() {
        fragment_home_recyclerview.apply {
            adapter = homeAdapter
            layoutManager = GridLayoutManager(activity, 2)
            addItemDecoration(
                GridSpacingItemDecoration(
                    spanCount = 2, spacing = 50, includeEdge = true
                )
            )
        }

        homeAdapter.items = homeAdapterItemManager.getItems()
    }
}