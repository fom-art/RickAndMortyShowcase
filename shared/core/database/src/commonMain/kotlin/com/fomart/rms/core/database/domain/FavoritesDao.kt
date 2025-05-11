package com.fomart.rms.core.database.domain

import kotlinx.coroutines.flow.Flow

interface FavoritesDao {
    suspend fun upsertCharacterId(favoriteCharacterId: String)
    suspend fun deleteCharacterId(favoriteCharacterId: String)
    fun getFavourites(): Flow<List<String>>
}