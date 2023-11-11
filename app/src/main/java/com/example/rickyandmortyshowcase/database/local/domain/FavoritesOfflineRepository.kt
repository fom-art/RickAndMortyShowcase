package com.example.rickyandmortyshowcase.database.local.domain

import com.example.rickyandmortyshowcase.database.local.data.Favorite
import com.example.rickyandmortyshowcase.database.local.data.FavoriteDao
import kotlinx.coroutines.flow.Flow

class FavoritesOfflineRepository(private val favoriteDao: FavoriteDao): FavoritesRepository {
    override suspend fun upsertCharacter(favorite: Favorite) = favoriteDao.upsertCharacter(favorite)

    override suspend fun deleteCharacter(favorite: Favorite) = favoriteDao.deleteCharacter(favorite)

    override fun getFavourites(): Flow<List<String>> = favoriteDao.getFavourites()
}