package com.droidlabs.core.network.transaction.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.droidlabs.core.network.transaction.domain.model.BLOCKCHAIN_TABLE_NAME
import com.droidlabs.core.network.transaction.domain.model.BlockchainMultiAddress

@Dao
interface BlockchainDao {

    @Query("Select * from $BLOCKCHAIN_TABLE_NAME")
    fun getBlockchainMultiAddress(): List<BlockchainMultiAddress>

    @Insert
    fun insertBlockchainMultiAddress(blockchainMultiAddress: BlockchainMultiAddress)

    @Update
    fun updateBlockchainMultiAddress(blockchainMultiAddress: BlockchainMultiAddress)

    @Delete
    fun deleteBlockchainMultiAddress(blockchainMultiAddress: BlockchainMultiAddress)
}
