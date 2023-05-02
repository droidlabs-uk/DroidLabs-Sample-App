package com.droidlabs.core.network

import javax.inject.Inject

class ExceptionHandlerImpl @Inject constructor(): ExceptionHandler {
    override fun handle(it: Throwable) {
        TODO("Not yet implemented")
    }
}

interface ExceptionHandler {
    fun handle(it: Throwable)
}
