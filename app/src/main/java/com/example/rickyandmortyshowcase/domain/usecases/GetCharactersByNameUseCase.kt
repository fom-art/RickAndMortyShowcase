package com.example.rickyandmortyshowcase.domain.usecases

import com.example.rickyandmortyshowcase.domain.client.RickAndMortyShowcaseClient
import com.example.rickyandmortyshowcase.domain.entities.CharacterSimple

class GetCharactersByNameUseCase(
    private val rickAndMortyShowcaseClient: RickAndMortyShowcaseClient
) {
    suspend fun execute(name: String): List<CharacterSimple> {
        return rickAndMortyShowcaseClient.getCharactersByName(name)
    }
}