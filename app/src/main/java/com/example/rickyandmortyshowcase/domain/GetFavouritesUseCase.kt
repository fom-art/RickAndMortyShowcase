package com.example.rickyandmortyshowcase.domain

import com.example.rickyandmortyshowcase.data.FavoritesRepository
import kotlinx.coroutines.flow.Flow

class GetFavouritesUseCase(private val favoritesRepository: FavoritesRepository) {
    suspend operator fun invoke(): Flow<List<String>> {
        return favoritesRepository.getFavourites();
    }
}