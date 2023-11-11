package com.example.rickyandmortyshowcase.database.local.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Favorite::class], version = 1)

abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao // Replace with your DAO class
}