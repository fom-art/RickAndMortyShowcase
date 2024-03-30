package com.example.rickyandmortyshowcase.characters.domain

import com.example.rickyandmortyshowcase.characters.data.CharactersRepository

class GetCharactersByNameUseCase(
    private val charactersRepository: CharactersRepository
) {
    suspend operator fun invoke(name: String): List<CharacterSimple> {
        return charactersRepository.getCharactersByName(name)
    }
}