package com.droidlabs.core.network

abstract class Cache(private val exceptionHandler: ExceptionHandler) {

    protected suspend fun <T> runQuery(query: suspend () -> T) =
        query.runCatching { invoke() }
            .onFailure { exceptionHandler.handle(it) }
            .getOrNull()
}
