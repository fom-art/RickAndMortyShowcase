package com.fomart.rms.core.model.domain.error

open class Error {
    data class Unexpected(val exception: Exception): Error()
}
