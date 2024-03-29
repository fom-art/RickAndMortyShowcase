package com.example.rickyandmortyshowcase.data.local.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.rickyandmortyshowcase.data.local.data.Favorite
import dagger.Provides
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface FavoriteDao {
        @Upsert
        suspend fun upsertCharacter(favorite: Favorite)

        @Delete
        suspend fun  deleteCharacter(favorite: Favorite)

        @Query("SELECT * FROM favorite")
        fun getFavourites(): Flow<List<String>>
}