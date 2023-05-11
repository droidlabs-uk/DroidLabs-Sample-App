package com.droidlabs.core.network.transaction.domain.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

const val BLOCKCHAIN_TABLE_NAME = "Blockchain"

@Entity(tableName = BLOCKCHAIN_TABLE_NAME)
@Serializable
class BlockchainMultiAddress(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo
    @SerialName("addresses")
    val addresses: MutableList<Address>,

    @Embedded
    @SerialName("wallet")
    val wallet: Wallet,

    @ColumnInfo
    @SerialName("txs")
    val txs: MutableList<Txs>,

    @Embedded
    @SerialName("info")
    val info: Info
)

@Entity
@Serializable
class Address(

    @PrimaryKey
    @ColumnInfo
    @SerialName("address")
    val address: String,

    @ColumnInfo
    @SerialName("total_received")
    val total_received: Int,

    @ColumnInfo
    @SerialName("total_sent")
    val total_sent: Int,

    @ColumnInfo
    @SerialName("final_balance")
    val final_balance: Int
)

@Entity
@Serializable
class Wallet(

    @ColumnInfo
    @SerialName("final_balance")
    val final_balance: Int
)

@Entity
@Serializable
class Txs(

    @ColumnInfo
    @SerialName("result")
    val result: Int,

    @ColumnInfo
    @SerialName("time")
    val time: Int,

    @PrimaryKey
    @ColumnInfo
    @SerialName("hash")
    val hash: String,

    @ColumnInfo
    @SerialName("fee")
    val fee: Int
)

@Entity
@Serializable
class Info(

    @ColumnInfo
    @SerialName("conversion")
    val conversion: String
)
