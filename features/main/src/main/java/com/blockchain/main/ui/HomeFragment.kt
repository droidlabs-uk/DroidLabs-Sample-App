package com.blockchain.main.ui

import android.R.attr.spacing
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.blockchain.main.R
import com.blockchain.main.ui.adapter.DelegatingMainAdapter
import com.blockchain.main.ui.adapter.HomeAdapterItemManager
import com.blockchain.main.ui.adapter.MainActions
import com.blockchain.main.utils.GridSpacingItemDecoration
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private val homeAdapter = DelegatingMainAdapter(){ actions ->
        when(actions){
            MainActions.CuvvaFeatureClicked -> TODO()
            MainActions.TransactionsFeatureClicked -> TODO()
            MainActions.RxFeatureClicked -> TODO()
            MainActions.CoroutineFeatureClicked -> TODO()
            MainActions.BreakingBadFeatureClicked -> TODO()
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
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun initHomeRecyclerView() {
        fragment_home_recyclerview.apply {
            adapter = homeAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(
                GridSpacingItemDecoration(
                    spanCount = 2, spacing = 50, includeEdge = true
                )
            )
        }

        homeAdapter.items = homeAdapterItemManager.getItems()
    }
}