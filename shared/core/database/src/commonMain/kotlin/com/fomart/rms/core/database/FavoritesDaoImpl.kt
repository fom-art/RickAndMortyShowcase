package com.fomart.rms.core.database

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.fomart.rms.core.database.domain.FavoritesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class FavoritesDaoImpl(
    private val queries: FavouriteQueries
): FavoritesDao {
    override suspend fun upsertCharacterId(favoriteCharacterId: String) {
        queries.insertFavourite(favoriteCharacterId)
    }

    override suspend fun deleteCharacterId(favoriteCharacterId: String) {
        queries.deleteFavourite(favoriteCharacterId)
    }

    override fun getFavourites(): Flow<List<String>> {
        return queries.selectAllFavourites()
            .asFlow()
            .mapToList(Dispatchers.IO)
    }
}