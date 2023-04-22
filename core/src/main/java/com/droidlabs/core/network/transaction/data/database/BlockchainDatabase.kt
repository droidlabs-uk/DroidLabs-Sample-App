package com.droidlabs.core.network.transaction.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.droidlabs.core.network.transaction.domain.model.BlockchainMultiAddress

@Database(entities = [BlockchainMultiAddress::class], version = 1)
abstract class BlockchainDatabase : RoomDatabase(){
    abstract fun blockchainDao(): BlockchainDao
}
