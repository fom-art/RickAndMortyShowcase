package com.example.rickyandmortyshowcase.database.local.data

import androidx.room.Database
import com.example.rickyandmortyshowcase.database.local.domain.FavouriteDao

@Database(
    entities = [Favourite::class],
    version = 1
)
abstract class FavouriteDatabase {
    abstract val dao: FavouriteDao
}