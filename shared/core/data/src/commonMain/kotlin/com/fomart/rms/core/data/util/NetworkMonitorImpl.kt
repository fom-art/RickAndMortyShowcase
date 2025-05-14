package com.fomart.rms.core.data.util

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive

class NetworkMonitorImpl(private val client: HttpClient) : NetworkMonitor {
    override suspend fun checkConnection(url: String): Flow<Boolean> {
        val intervalMillis = 5000L
        return flow {
            while (currentCoroutineContext().isActive) {
                val isConnected = try {
                    val response: HttpResponse = client.get(url)
                    response.status.value == 200
                } catch (e: Exception) {
                    false
                }
                emit(isConnected)
                delay(intervalMillis)
            }
        }
    }
}