package com.example.rickyandmortyshowcase.characters.domain

import com.example.rickyandmortyshowcase.characters.data.CharactersRepository

class GetCharactersUseCase(
    private val charactersRepository: CharactersRepository
) {
    suspend operator fun invoke(): List<CharacterSimple> {
        return charactersRepository.getCharacters()
    }
}