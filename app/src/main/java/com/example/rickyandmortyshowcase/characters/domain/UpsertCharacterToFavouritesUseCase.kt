package com.example.rickyandmortyshowcase.characters.domain

import com.example.rickyandmortyshowcase.characters.data.CharactersRepository
import com.example.rickyandmortyshowcase.characters.data.local.Favourite

class UpsertCharacterToFavouritesUseCase(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(id: String) {
        return charactersRepository.upsertCharacterToFavourites(Favourite(id));
    }
}