package com.example.rickyandmortyshowcase.domain

import com.example.rickyandmortyshowcase.data.FavoritesRepository

class GetFavouritesUseCase(private val favoritesRepository: FavoritesRepository) {
    suspend operator fun invoke(): List<String> {
        return favoritesRepository.getFavourites();
    }
}