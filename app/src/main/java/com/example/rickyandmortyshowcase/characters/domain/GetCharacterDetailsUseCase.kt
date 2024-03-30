package com.example.rickyandmortyshowcase.characters.domain

import com.example.rickyandmortyshowcase.characters.data.CharactersRepository

class GetCharacterDetailsUseCase(
    private val charactersRepository: CharactersRepository
) {
    suspend operator fun invoke(id: String): CharacterDetailed? {
        return charactersRepository.getCharacterDetails(id)
    }
}