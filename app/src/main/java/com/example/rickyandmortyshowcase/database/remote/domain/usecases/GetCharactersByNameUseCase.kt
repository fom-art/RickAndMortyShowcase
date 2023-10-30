package com.example.rickyandmortyshowcase.database.remote.domain.usecases

import com.example.rickyandmortyshowcase.database.remote.data.client.ApolloRickAndMortyShowcaseClient
import com.example.rickyandmortyshowcase.database.remote.domain.entities.client.RickAndMortyShowcaseClient
import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterSimple

class GetCharactersByNameUseCase(
    private val rickAndMortyShowcaseClient: ApolloRickAndMortyShowcaseClient
) {
    suspend fun execute(name: String): List<CharacterSimple> {
        return rickAndMortyShowcaseClient.getCharactersByName(name)
    }
}