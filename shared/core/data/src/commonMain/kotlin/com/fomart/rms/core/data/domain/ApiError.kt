package com.fomart.rms.core.data.domain

import com.fomart.rms.core.model.domain.error.Error

sealed class ApiError : Error() {
    data class Network(val cause: Throwable? = null) : ApiError()
    data class Timeout(val durationMs: Long? = null) : ApiError()
    data class GraphQL(val message: String, val locations: List<String>? = null) : ApiError()
    data class Http(val statusCode: Int, val message: String? = null) : ApiError()
    object Unauthorized : ApiError()
    object Forbidden : ApiError()
    object NotFound : ApiError()
    data class Unexpected(val cause: Throwable? = null) : ApiError()
}