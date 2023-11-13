package com.example.rickyandmortyshowcase.local_database_tests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.rickyandmortyshowcase.database.local.data.Favorite
import com.example.rickyandmortyshowcase.database.local.data.FavoriteDao
import com.example.rickyandmortyshowcase.database.local.data.FavoriteDao_Impl
import com.example.rickyandmortyshowcase.database.local.data.FavoriteDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class RaMSDaoTests {
    private lateinit var favoriteDao: FavoriteDao
    private lateinit var db: FavoriteDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, FavoriteDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        favoriteDao = FavoriteDao_Impl(db)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Throws(Exception::class)
    fun roomClientDatabase_insertCharacter_isAddedToDatabase() = runBlocking {
        val id = "25062004"
        val favorite = Favorite(id)
        favoriteDao.upsertCharacter(favorite)
        val allFavorites = favoriteDao.getFavourites().first()
        assert(allFavorites[0] == id)
    }

    @Test
    @Throws(Exception::class)
    fun roomClientDatabase_deleteInsertedCharacter_isRemoved() = runBlocking {
        val id = "20042506"

        //Add to database
        val favorite = Favorite(id)
        favoriteDao.upsertCharacter(favorite)
        val allFavoritesAfterInsert = favoriteDao.getFavourites().first()
        assert(allFavoritesAfterInsert[0] == id)

        //Remove from database
        favoriteDao.deleteCharacter(favorite)
        val allFavoritesAfterDelete= favoriteDao.getFavourites().first()
        assert(allFavoritesAfterDelete.firstOrNull{it == id} == null)
    }
}