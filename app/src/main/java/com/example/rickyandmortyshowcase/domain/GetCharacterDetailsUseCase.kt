package com.example.rickyandmortyshowcase.domain

import com.example.rickyandmortyshowcase.data.CharactersRepository

class GetCharacterDetailsUseCase(
    private val charactersRepository: CharactersRepository
) {
    suspend operator fun invoke(id: String): CharacterDetailed? {
        return charactersRepository.getCharacterDetails(id)
    }
}