package com.droidlabs.core.network.transaction.domain.model

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class BlockchainTypeConverters {
    @TypeConverter
    fun stringToAddressList(value: String): MutableList<Address> = Json.decodeFromString(value)

    @TypeConverter
    fun addressListToString(list: MutableList<Address>): String = Json.encodeToString(list)

    @TypeConverter
    fun stringToTxsList(value: String): MutableList<Txs> = Json.decodeFromString(value)

    @TypeConverter
    fun txsListToString(list: MutableList<Txs>): String = Json.encodeToString(list)
}
