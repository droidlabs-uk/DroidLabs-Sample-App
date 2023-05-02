package com.droidlabs.core.network.transaction.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.droidlabs.core.network.transaction.data.local.BlockchainDao
import com.droidlabs.core.network.transaction.domain.model.BlockchainMultiAddress

private const val DATABASE_VERSION = 1

@Database(
    entities = [BlockchainMultiAddress::class],
    version = DATABASE_VERSION
)
abstract class BlockchainDatabase : RoomDatabase() {
    abstract fun blockchainDao(): BlockchainDao
}
