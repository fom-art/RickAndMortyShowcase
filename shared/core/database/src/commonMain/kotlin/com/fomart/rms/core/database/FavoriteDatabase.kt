package com.fomart.rms.core.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Favourite::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {


    abstract fun favoriteDao(): FavoritesDao
}