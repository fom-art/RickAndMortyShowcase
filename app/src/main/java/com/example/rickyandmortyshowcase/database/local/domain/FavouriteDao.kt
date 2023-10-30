package com.example.rickyandmortyshowcase.database.local.domain

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.rickyandmortyshowcase.database.local.data.Favourite

@Dao
interface FavouriteDao {
        @Upsert
        suspend fun upsertCharacter(favourite: Favourite)

        @Delete
        suspend fun  deleteCharacter(favourite: Favourite)

        @Query("SELECT * FROM favourite")
        fun getFavourites(): kotlinx.coroutines.flow.Flow<List<String>>
}