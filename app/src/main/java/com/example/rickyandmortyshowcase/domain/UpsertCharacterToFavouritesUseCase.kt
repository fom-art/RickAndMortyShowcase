package com.example.rickyandmortyshowcase.domain

import com.example.rickyandmortyshowcase.data.CharactersRepository
import com.example.rickyandmortyshowcase.data.local.Favourite

class UpsertCharacterToFavouritesUseCase(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(id: String) {
        return charactersRepository.upsertCharacterToFavourites(Favourite(id));
    }
}