package com.example.rickyandmortyshowcase.characters.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Favourite::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {


    abstract fun favoriteDao(): FavoritesDao
}