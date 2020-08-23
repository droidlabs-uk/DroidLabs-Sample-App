package com.droidlabs.cuvva.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.droidlabs.cuvva.PolicyViewModel
import com.droidlabs.cuvva.R
import com.droidlabs.cuvva.ui.home.adapter.TransactionsAdapter
import com.droidlabs.cuvva.ui.home.policyTransformer.PolicyTransformer
import com.droidlabs.cuvva.ui.utils.CustomDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*

class CuvvaFragment : Fragment() {

    private val transactionsAdapter = TransactionsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        layoutInflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTransactionsRecyclerView()
    }

    override fun onStart() {
        super.onStart()

        /*
         Created new & validated JSON from the given response:
         */
        // http://www.mocky.io/v2/5cb893314c0000ad1cd3d68f

        val policy = "5cb893314c0000ad1cd3d68f"

        ViewModelProviders
            .of(this)
            .get(PolicyViewModel::class.java)
            .apply {
                fetchPolicy(policy)
                policyLiveData.observe(viewLifecycleOwner, Observer {
                    transactionsAdapter.vehiclePolicies = PolicyTransformer().transform(it)
                })
            }
    }

    private fun initTransactionsRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)

        fragment_cuvva_recyclerview.apply {
            adapter = transactionsAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(CustomDividerItemDecoration(context, linearLayoutManager.orientation))
        }
    }
}