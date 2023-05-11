package com.droidlabs.core.network.transaction.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.droidlabs.core.network.BaseDao
import com.droidlabs.core.network.transaction.domain.model.BLOCKCHAIN_TABLE_NAME
import com.droidlabs.core.network.transaction.domain.model.BlockchainMultiAddress

@Dao
interface BlockchainDao : BaseDao<BlockchainMultiAddress> {

    @Query("SELECT * FROM $BLOCKCHAIN_TABLE_NAME")
    suspend fun getBlockchainMultiAddress(): List<BlockchainMultiAddress>

    @Insert
    suspend fun insertBlockchainMultiAddress(blockchainMultiAddress: BlockchainMultiAddress)

    @Update
    suspend fun updateBlockchainMultiAddress(blockchainMultiAddress: BlockchainMultiAddress)

    @Delete
    suspend fun deleteBlockchainMultiAddress(blockchainMultiAddress: BlockchainMultiAddress)

    @Query("DELETE FROM $BLOCKCHAIN_TABLE_NAME")
    suspend fun deleteAll()

    @Query("SELECT * FROM $BLOCKCHAIN_TABLE_NAME WHERE id=:id")
    fun getBlockchainMultiAddressById(id: Long): BlockchainMultiAddress

//    @Query("SELECT * FROM $BLOCKCHAIN_TABLE_NAME WHERE addresses IN (:address)")
    @Query("SELECT * FROM $BLOCKCHAIN_TABLE_NAME WHERE addresses IN (:address)") //TODO: needs to be correct with embedded
    fun getBlockchainMultiAddressByAddress(address: String): BlockchainMultiAddress
}

@Transaction
suspend fun BlockchainDao.replaceAll(vararg blockchainMultiAddress: BlockchainMultiAddress) {
    deleteAll()
    insert(*blockchainMultiAddress)
}
