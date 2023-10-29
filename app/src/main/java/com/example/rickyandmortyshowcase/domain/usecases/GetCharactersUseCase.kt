package com.example.rickyandmortyshowcase.domain.usecases

import com.example.rickyandmortyshowcase.domain.client.RickAndMortyShowcaseClient
import com.example.rickyandmortyshowcase.domain.entities.CharacterSimple

class GetCharactersUseCase(
    private val rickAndMortyShowcaseClient: RickAndMortyShowcaseClient
) {
    suspend fun execute(): List<CharacterSimple> {
        return rickAndMortyShowcaseClient.getCharacters()
    }
}