package com.example.rickyandmortyshowcase.data

import com.example.rickyandmortyshowcase.data.local.data.Favorite
import com.example.rickyandmortyshowcase.data.local.data.FavoriteDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.single

class FavoritesRepository(private val localFavouritesDataSource: LocalFavouritesDataSource) {
    suspend fun upsertCharacter(favorite: Favorite) = localFavouritesDataSource.upsertCharacter(favorite)

    suspend fun deleteCharacter(favorite: Favorite) = localFavouritesDataSource.deleteCharacter(favorite)

    suspend fun getFavourites(): Flow<List<String>> = localFavouritesDataSource.getFavourites()
}