package com.example.rickyandmortyshowcase.domain

import com.example.rickyandmortyshowcase.data.CharactersRepository

class GetCharactersUseCase(
    private val charactersRepository: CharactersRepository
) {
    suspend operator fun invoke(): List<CharacterSimple> {
        return charactersRepository.getCharacters()
    }
}