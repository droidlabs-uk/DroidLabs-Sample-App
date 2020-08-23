package com.droidlabs.cuvva.ui.home.policyTransformer

import com.droidlabs.core.network.policy.datamodel.Policy
import com.droidlabs.core.network.policy.datamodel.Vehicle
import com.droidlabs.cuvva.R

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
                active = hasActivePolicy(vehiclePolicyList),
                icon = setBrandIcon(vehicleMake),
                make = vehicleMake,
                type = setVehicleType(vehicle.payload.vehicle),
                policyAction = "Insure",
                pretty_reg_plate = vehicle.payload.vehicle.prettyVrm,
                total_policies = vehiclePolicyList.size,
                remaining_time = "",
                remaining_time_percent = 0
            )

            vehiclePolicies.add(item)
        }

        return vehiclePolicies
    }

    private fun setVehicleType(vehicle: Vehicle) = "${vehicle.color} ${vehicle.model}"

    private fun hasActivePolicy(vehiclePolicyList: List<Policy>): Boolean {
        return false
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