package com.blockchain.cuvva.ui.home.policyTransformer

import com.blockchain.core.network.policy.datamodel.Policy
import com.blockchain.cuvva.R

class PolicyTransformer{
    fun transform(policyList: List<Policy>): List<VehiclePolicy> {
        val vehiclePolicies = arrayListOf<VehiclePolicy>()


        val cleanedList = policyList.filter { it.type.toUpperCase().compareTo(PolicyTypes.POLICY_CREATED.name) == 0 }

        val uniqueVehiclesVrms =
            cleanedList
                .distinctBy { it.payload.vehicle.vrm }
                .map { it.payload.vehicle.vrm }


        for (vrm in uniqueVehiclesVrms){

            val vehiclePolicyList = cleanedList.filter { it.payload.vehicle.vrm == vrm }

            val vehicle = vehiclePolicyList.first()
            val vehicleMake = vehicle.payload.vehicle.make

            val item = VehiclePolicy(
                icon = setBrandIcon(vehicleMake),
                make = vehicleMake,
                type = vehicle.payload.vehicle.model,
                policyAction = "",
                pretty_reg_plate = vehicle.payload.vehicle.prettyVrm,
                total_policies = vehiclePolicyList.size,
                remaining_time = "",
                remaining_time_percent = 0
            )

            vehiclePolicies.add(item)
        }

        return vehiclePolicies
    }

    private fun setBrandIcon(vehicleMake: String): Int {

        val make = CarTypes.valueOf(vehicleMake.replace("-", "_").toUpperCase())

        return when(make) {
            CarTypes.VOLKSWAGEN -> R.drawable.carlogo_volkswagen
            CarTypes.MINI -> R.drawable.carlogo_mini
            CarTypes.MERCEDES_BENZ -> R.drawable.carlogo_mercedes
            CarTypes.NISSAN -> R.drawable.carlogo_volkswagen
            else -> R.drawable.carlogo_placeholder
        }
    }

    private fun calulateRemainingTime(){

    }
}