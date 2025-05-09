package com.example.rickyandmortyshowcase.characters.domain

import com.example.rickyandmortyshowcase.characters.data.CharactersRepository
import kotlinx.coroutines.flow.Flow

class GetFavouritesUseCase(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(): Flow<List<String>> {
        return charactersRepository.getFavourites();
    }
}