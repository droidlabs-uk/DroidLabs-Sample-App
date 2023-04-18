package com.droidlabs.cuvva.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.droidlabs.cuvva.PolicyViewModel
import com.droidlabs.cuvva.R
import com.droidlabs.cuvva.databinding.FragmentCuvvaBinding
import com.droidlabs.cuvva.ui.home.adapter.TransactionsAdapter
import com.droidlabs.cuvva.ui.home.policyTransformer.PolicyTransformer
import com.droidlabs.cuvva.ui.utils.CustomDividerItemDecoration

class CuvvaFragment : Fragment(R.layout.fragment_cuvva) {

    private lateinit var binding: FragmentCuvvaBinding

    private val transactionsAdapter = TransactionsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCuvvaBinding.bind(view)

        initTransactionsRecyclerView()
    }

    override fun onStart() {
        super.onStart()

        /*
         Created new & validated JSON from the given response:
         */
        // http://www.mocky.io/v2/5cb893314c0000ad1cd3d68f

        val policy = "5cb893314c0000ad1cd3d68f"

        ViewModelProvider(this)[PolicyViewModel::class.java]
            .apply {
                fetchPolicy(policy)
                policyLiveData.observe(viewLifecycleOwner) {
                    transactionsAdapter.vehiclePolicies = PolicyTransformer().transform(it)
                }
            }
    }

    private fun initTransactionsRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)

        binding.fragmentCuvvaRecyclerview.apply {
            adapter = transactionsAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(CustomDividerItemDecoration(context, linearLayoutManager.orientation))
        }
    }
}