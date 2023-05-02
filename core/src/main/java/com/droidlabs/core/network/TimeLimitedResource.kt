package com.droidlabs.core.network

import java.util.Date

interface TimeLimitedResource {
    var refreshRate: Long
    val lastUpdateDate: Date?

    suspend fun evict(cleanup: Boolean = false)
}
