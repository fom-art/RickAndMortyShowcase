package com.example.rickyandmortyshowcase.domain

import com.example.rickyandmortyshowcase.data.CharactersRepository
import kotlinx.coroutines.flow.Flow

class GetFavouritesUseCase(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(): Flow<List<String>> {
        return charactersRepository.getFavourites();
    }
}