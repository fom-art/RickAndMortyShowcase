package com.example.rickyandmortyshowcase.local_database_tests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.rickyandmortyshowcase.data.local.Favourite
import com.example.rickyandmortyshowcase.data.local.FavoritesDao
import com.example.rickyandmortyshowcase.data.local.FavoriteDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class RaMSDaoTests {
    private lateinit var favoritesDao: FavoritesDao
    private lateinit var db: FavoriteDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, FavoriteDatabase::class.java)
            .allowMainThreadQueries()
            .build()
//        favoriteDao = FavoriteDao_Impl(db)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Throws(Exception::class)
    fun roomClientDatabase_insertCharacter_isAddedToDatabase() = runBlocking {
        val id = "25062004"
        val favourite = Favourite(id)
        favoritesDao.upsertCharacter(favourite)
        val allFavorites = favoritesDao.getFavourites().first()
        assert(allFavorites[0] == id)
    }

    @Test
    @Throws(Exception::class)
    fun roomClientDatabase_deleteInsertedCharacter_isRemoved() = runBlocking {
        val id = "20042506"

        //Add to database
        val favourite = Favourite(id)
        favoritesDao.upsertCharacter(favourite)
        val allFavoritesAfterInsert = favoritesDao.getFavourites().first()
        assert(allFavoritesAfterInsert[0] == id)

        //Remove from database
        favoritesDao.deleteCharacter(favourite)
        val allFavoritesAfterDelete= favoritesDao.getFavourites().first()
        assert(allFavoritesAfterDelete.firstOrNull{it == id} == null)
    }
}