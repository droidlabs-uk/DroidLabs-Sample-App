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
                    homeAdapter.vehicles = PolicyTransformer().transform(it)
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
    var vehicles: List<VehiclePolicy> = listOf()
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

    override fun getItemCount() = vehicles.size

    override fun onBindViewHolder(holder: TransactionsViewholder, position: Int) {
        val policy = vehicles[position]

        val vehicleMake = policy.payload.vehicle.make
        holder.icon.setImageResource(setBrandIcon(vehicleMake))
        holder.brand.text = vehicleMake

        holder.type.text = policy.payload.vehicle.model
        holder.reg_plate.text = setRegPlate(policy.payload.vehicle.prettyVrm)
    }

    private fun setBrandIcon(vehicleMake: String): Int {

        val make = CarTypes.valueOf(vehicleMake.replace("-", "_").toUpperCase())

        return when(make) {
            CarTypes.VOLKSWAGEN -> R.drawable.carlogo_volkswagen
            CarTypes.MINI -> R.drawable.carlogo_mini
            CarTypes.MERCEDES_BENZ -> R.drawable.carlogo_mercedes
            CarTypes.NISSAN -> R.drawable.carlogo_volkswagen
        }
    }

    private fun setRegPlate(regPlate: String) = "Reg Plate\n${regPlate}"
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

enum class PolicyTypes{
    POLICY_CREATED,
    POLICY_FINANCIAL_TRANSACTION,
    UNKNOWN
}

enum class CarTypes{
    VOLKSWAGEN,
    MINI,
    MERCEDES_BENZ,
    NISSAN
}

data class VehiclePolicy(

)

class PolicyTransformer{
    fun transform(policyList: List<Policy>): List<VehiclePolicy> {



        it.filter { it.type.toUpperCase().compareTo(PolicyTypes.POLICY_CREATED.name) == 0 }
    }
}