package com.example.rickyandmortyshowcase.database.local.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickyandmortyshowcase.database.local.domain.FavoriteDao


@Database(entities = [Favorite::class], version = 1) // Replace 'Favourite' with your entity class

abstract class FavoriteDatabase : RoomDatabase() {
    // Define your abstract methods for accessing DAOs here.
    abstract fun favouriteDao(): FavoriteDao? // Replace with your DAO class
}