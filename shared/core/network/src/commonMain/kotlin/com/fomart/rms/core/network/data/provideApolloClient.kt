package com.fomart.rms.core.network.data

import com.apollographql.apollo.ApolloClient

fun provideApolloClient(): ApolloClient {
    return ApolloClient.Builder()
        .serverUrl("https://rickandmortyapi.com/graphql")
        .build()
}