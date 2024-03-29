package com.example.rickyandmortyshowcase.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {
        @Upsert
        suspend fun upsertCharacter(favourite: Favourite)

        @Delete
        suspend fun  deleteCharacter(favourite: Favourite)

        @Query("SELECT * FROM favourite")
        fun getFavourites(): Flow<List<String>>
}