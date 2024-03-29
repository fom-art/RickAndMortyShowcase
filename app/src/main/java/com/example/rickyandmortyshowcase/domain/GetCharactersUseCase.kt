package com.example.rickyandmortyshowcase.domain

import com.example.rickyandmortyshowcase.data.CharactersRepository
import com.example.rickyandmortyshowcase.data.client.RickAndMortyShowcaseClient
import com.example.rickyandmortyshowcase.data.model.CharacterSimple

class GetCharactersUseCase(
    private val charactersRepository: CharactersRepository
) {
    suspend operator fun invoke(): List<CharacterSimple> {
        return charactersRepository.getCharacters()
    }
}