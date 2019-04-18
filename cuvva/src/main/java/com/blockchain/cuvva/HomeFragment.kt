package com.blockchain.cuvva

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blockchain.core.network.policy.datamodel.Policy
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.itemview_home.view.*

class HomeFragment: Fragment() {

    private val homeAdapter by lazy { HomeAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        layoutInflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initHomeRecyclerView()
    }

    override fun onStart() {
        super.onStart()

        val policy = "5c697fec370000a90a07fd64"

        ViewModelProviders.of(this).get(PolicyViewModel::class.java).apply {
            fetchPolicy(policy)
            policyLiveData.observe(viewLifecycleOwner, Observer {
                homeAdapter.policies = it.policies
            })
        }
    }

    private fun initHomeRecyclerView(){
        val linearLayoutManager = LinearLayoutManager(context)

        fragment_home_recyclerview.apply {
            adapter = homeAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))
        }
    }
}

class HomeAdapter: RecyclerView.Adapter<TransactionsViewholder>() {
    var policies: List<Policy> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TransactionsViewholder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.itemview_home,
                parent,
                false
            )

        )

    override fun getItemCount() = policies.size

    override fun onBindViewHolder(holder: TransactionsViewholder, position: Int) {
        val policy = policies[position]

        holder.brand.text = policy.payLoad.vehicle.make
    }
}

class TransactionsViewholder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val icon: ImageView = itemView.itemview_home_icon
    val brand: TextView = itemView.itemview_home_brand
    val type: TextView = itemView.itemview_home_type
    val policy_action: Button = itemView.itemview_home_policy_action
    val reg_plate: TextView = itemView.itemview_home_reg_plate
    val total_policies: TextView = itemView.itemview_home_total_policies
    val policy_time_remaining_percent: CircularProgressBar = itemView.itemview_home_policy_time_remaining_percent
    val policy_time_remaining_text: TextView = itemView.itemview_home_policy_time_remaining_text
}