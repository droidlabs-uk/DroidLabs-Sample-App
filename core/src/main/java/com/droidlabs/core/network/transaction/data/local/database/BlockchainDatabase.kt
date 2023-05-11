package com.droidlabs.core.network.transaction.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.droidlabs.core.network.transaction.data.local.BlockchainDao
import com.droidlabs.core.network.transaction.domain.model.BlockchainMultiAddress
import com.droidlabs.core.network.transaction.domain.model.BlockchainTypeConverters

private const val DATABASE_VERSION = 1

@Database(
    entities = [BlockchainMultiAddress::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(BlockchainTypeConverters::class)
abstract class BlockchainDatabase : RoomDatabase() {
    abstract fun blockchainDao(): BlockchainDao
}
