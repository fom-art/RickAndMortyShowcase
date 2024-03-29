package com.example.rickyandmortyshowcase.data

import com.example.rickyandmortyshowcase.data.local.data.Favorite
import com.example.rickyandmortyshowcase.data.local.data.FavoriteDao
import com.example.rickyandmortyshowcase.data.model.CharacterDetailed
import com.example.rickyandmortyshowcase.data.model.CharacterSimple
import kotlinx.coroutines.flow.Flow

class LocalFavouritesDataSource(private val favoriteDao: FavoriteDao) {
    suspend fun upsertCharacter(favorite: Favorite) = favoriteDao.upsertCharacter(favorite)

    suspend fun deleteCharacter(favorite: Favorite) = favoriteDao.deleteCharacter(favorite)

    fun getFavourites(): Flow<List<String>> = favoriteDao.getFavourites()
}