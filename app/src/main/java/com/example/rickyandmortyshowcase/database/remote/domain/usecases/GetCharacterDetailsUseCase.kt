package com.example.rickyandmortyshowcase.database.remote.domain.usecases

import com.example.rickyandmortyshowcase.database.remote.domain.entities.client.RickAndMortyShowcaseClient
import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterDetailed

class GetCharacterDetailsUseCase(
    private val rickAndMortyShowcaseClient: RickAndMortyShowcaseClient
) {
    suspend fun execute(id: String): CharacterDetailed? {
        return rickAndMortyShowcaseClient.getCharacterDetails(id)
    }
}