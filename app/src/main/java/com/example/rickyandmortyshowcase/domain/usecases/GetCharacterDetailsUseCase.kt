package com.example.rickyandmortyshowcase.domain.usecases

import com.example.rickyandmortyshowcase.domain.client.RickAndMortyShowcaseClient
import com.example.rickyandmortyshowcase.domain.entities.CharacterDetailed

class GetCharacterDetailsUseCase(
    private val rickAndMortyShowcaseClient: RickAndMortyShowcaseClient
) {
    suspend fun execute(id: String): CharacterDetailed? {
        return rickAndMortyShowcaseClient.getCharacterDetails(id)
    }
}