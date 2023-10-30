package com.example.rickyandmortyshowcase.database.local.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickyandmortyshowcase.database.local.domain.FavouriteDao


@Database(entities = [Favourite::class], version = 1) // Replace 'Favourite' with your entity class

abstract class FavouriteDatabase : RoomDatabase() {
    // Define your abstract methods for accessing DAOs here.
    abstract fun favouriteDao(): FavouriteDao? // Replace with your DAO class
}