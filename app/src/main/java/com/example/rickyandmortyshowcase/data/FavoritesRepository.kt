package com.example.rickyandmortyshowcase.data

import com.example.rickyandmortyshowcase.data.local.data.Favorite
import com.example.rickyandmortyshowcase.data.local.data.FavoriteDao

//class FavoritesRepository(private val favoriteDao: FavoriteDao) {
//    override suspend fun upsertCharacter(favorite: Favorite) = favoriteDao.upsertCharacter(favorite)
//
//    override suspend fun deleteCharacter(favorite: Favorite) = favoriteDao.deleteCharacter(favorite)
//
//    override fun getFavourites(): Flow<List<String>> = favoriteDao.getFavourites()
//}

class FavoritesRepository(private val localFavouritesDataSource: LocalFavouritesDataSource) {
    suspend fun upsertCharacter(favorite: Favorite) = localFavouritesDataSource.upsertCharacter(favorite)

    suspend fun deleteCharacter(favorite: Favorite) = localFavouritesDataSource.deleteCharacter(favorite)

    suspend fun getFavourites(): List<String> = localFavouritesDataSource.getFavourites().single()
}