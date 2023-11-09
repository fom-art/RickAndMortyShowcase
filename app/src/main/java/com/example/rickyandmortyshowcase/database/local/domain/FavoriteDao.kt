package com.example.rickyandmortyshowcase.database.local.domain

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.rickyandmortyshowcase.database.local.data.Favorite
import dagger.Provides

@Dao
interface FavoriteDao {
        @Upsert
        suspend fun upsertCharacter(favorite: Favorite)

        @Delete
        suspend fun  deleteCharacter(favorite: Favorite)

        @Query("SELECT * FROM favorite")
        fun getFavourites(): List<String>
}