package com.fomart.rms.core.network.di

import com.apollographql.apollo.ApolloClient
import com.fomart.rms.core.network.data.ApolloCharactersDataSource
import com.fomart.rms.core.network.data.provideApolloClient
import com.fomart.rms.core.network.domain.CharactersDataSource
import org.koin.dsl.module

val networkModule = module {
    single<ApolloClient> { provideApolloClient() }
    single<CharactersDataSource> { ApolloCharactersDataSource(apolloClient = get()) }
}