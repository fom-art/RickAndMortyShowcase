package com.fomart.rms.core.data.util

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {
    suspend fun checkConnection(url: String = "https://ktor.io/docs/"): Flow<Boolean>
}