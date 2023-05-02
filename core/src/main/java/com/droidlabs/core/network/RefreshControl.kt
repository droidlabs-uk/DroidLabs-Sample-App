package com.droidlabs.core.network

import java.util.Date
import java.util.concurrent.TimeUnit

class RefreshControl(
    rate: Long = DEFAULT_REFRESH_RATE_MS,
    private var _lastUpdateDate: Date? = null
) : TimeLimitedResource {

    private val listeners: MutableList<RefreshControlListener> = mutableListOf()
    private val children: MutableList<RefreshControl> = mutableListOf()

    override var refreshRate: Long = rate
    override val lastUpdateDate: Date?
        get() = _lastUpdateDate

    override suspend fun evict(cleanup: Boolean) {
        _lastUpdateDate = null
        children.forEach { it.evict(cleanup) }
        if (cleanup) {
            listeners.forEach { it.cleanup() }
        }
    }

    fun createChild(): RefreshControl =
        RefreshControl(refreshRate, _lastUpdateDate).also { children.add(it) }

    fun addListener(listener: RefreshControlListener) {
        listeners.add(listener)
    }

    fun refresh() {
        _lastUpdateDate = Date()
    }

    fun isExpired() = _lastUpdateDate?.let { (Date().time - it.time) > refreshRate } ?: true

    companion object {
        private val DEFAULT_REFRESH_RATE_MS = TimeUnit.MINUTES.toMillis(5)
    }
}

interface RefreshControlListener {
    suspend fun cleanup()
}
