package com.example.rickyandmortyshowcase.domain

import com.example.rickyandmortyshowcase.data.CharactersRepository
import com.example.rickyandmortyshowcase.data.client.RickAndMortyShowcaseClient
import com.example.rickyandmortyshowcase.data.model.CharacterSimple

class GetCharactersByNameUseCase(
    private val charactersRepository: CharactersRepository
) {
    suspend operator fun invoke(name: String): List<CharacterSimple> {
        return charactersRepository.getCharactersByName(name)
    }
}