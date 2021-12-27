package com.droidlabs.main.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.droidlabs.core.navigation.NavigationDest
import com.droidlabs.core.navigation.Navigator
import com.droidlabs.core.utils.GridSpacingItemDecoration
import com.droidlabs.main.R
import com.droidlabs.main.databinding.HomeFragmentBinding
import com.droidlabs.main.ui.adapter.DelegatingMainAdapter
import com.droidlabs.main.ui.adapter.HomeAdapterItemManager
import com.droidlabs.main.ui.adapter.MainActions.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {

    @Inject
    lateinit var navigator: Navigator

    private lateinit var binding: HomeFragmentBinding

    private val homeAdapter = DelegatingMainAdapter() { actions ->
        when (actions) {
            CuvvaFeatureClicked -> navigator.navigateTo(
                NavigationDest.Cuvva,
                requireContext()
            )

            TransactionsFeatureClicked -> navigator.navigateTo(
                NavigationDest.Transactions,
                requireContext()
            )

            BreakingBadFeatureClicked -> navigator.navigateTo(
                NavigationDest.BreakingBad,
                requireContext()
            )
        }
    }

    private val homeAdapterItemManager = HomeAdapterItemManager()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HomeFragmentBinding.bind(view)

        initHomeRecyclerView()
    }

    private fun initHomeRecyclerView() {
        binding.fragmentHomeRecyclerview.apply {
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