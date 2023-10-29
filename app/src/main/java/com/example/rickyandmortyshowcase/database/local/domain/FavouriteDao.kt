package com.example.rickyandmortyshowcase.database.local.domain

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface FavouriteDao {
        @Upsert
        suspend fun upsertCharacter(characterId: String)

        @Delete
        suspend fun  deleteCharacter(characterId: String)

        @Query("SELECT * FROM favourite")
        fun getFavourites(): kotlinx.coroutines.flow.Flow<List<String>>
}