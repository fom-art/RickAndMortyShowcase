package com.fomart.rms.core.data.di

import com.fomart.rms.core.data.data.CharactersRepositoryImpl
import com.fomart.rms.core.data.domain.repository.CharactersRepository
import com.fomart.rms.core.database.di.databaseModule
import com.fomart.rms.core.network.di.networkModule
import com.fomart.rms.core.data.util.NetworkMonitor
import com.fomart.rms.core.data.util.NetworkMonitorImpl
import com.fomart.rms.core.data.util.httpClient
import io.ktor.client.HttpClient
import org.koin.dsl.module

val dataModule = module {
    includes(
        networkModule,
        databaseModule
    )
    single<HttpClient> { httpClient }
    single<NetworkMonitor> { NetworkMonitorImpl(client = get()) }
    single<CharactersRepository> { CharactersRepositoryImpl(
        charactersDataSource = get(),
        favoritesDao = get()
    ) }
}