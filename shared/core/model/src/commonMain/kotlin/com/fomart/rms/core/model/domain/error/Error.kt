package com.fomart.rms.core.model.domain.error

open class Error {
    data class UnexpectedException(val exception: Exception): Error()
    data object Unexpected: Error()
}
