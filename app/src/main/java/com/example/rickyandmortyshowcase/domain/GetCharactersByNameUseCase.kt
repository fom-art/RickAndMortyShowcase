package com.example.rickyandmortyshowcase.domain

import com.example.rickyandmortyshowcase.data.CharactersRepository

class GetCharactersByNameUseCase(
    private val charactersRepository: CharactersRepository
) {
    suspend operator fun invoke(name: String): List<CharacterSimple> {
        return charactersRepository.getCharactersByName(name)
    }
}