package com.example.rickyandmortyshowcase.domain

import com.example.rickyandmortyshowcase.data.CharactersRepository
import com.example.rickyandmortyshowcase.data.client.RickAndMortyShowcaseClient
import com.example.rickyandmortyshowcase.data.model.CharacterDetailed

class GetCharacterDetailsUseCase(
    private val charactersRepository: CharactersRepository
) {
    suspend operator fun invoke(id: String): CharacterDetailed? {
        return charactersRepository.getCharacterDetails(id)
    }
}