package com.example.rickyandmortyshowcase.database.remote.domain.usecases

import com.example.rickyandmortyshowcase.database.remote.domain.entities.client.RickAndMortyShowcaseClient
import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterSimple

class GetCharactersUseCase(
    private val rickAndMortyShowcaseClient: RickAndMortyShowcaseClient
) {
    suspend fun execute(): List<CharacterSimple> {
        return rickAndMortyShowcaseClient.getCharacters()
    }
}