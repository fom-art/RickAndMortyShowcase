package com.example.rickyandmortyshowcase.data

import com.example.rickyandmortyshowcase.data.local.FavoritesDao
import com.example.rickyandmortyshowcase.data.local.Favourite
import com.example.rickyandmortyshowcase.data.network.RemoteCharactersDataSource
import com.example.rickyandmortyshowcase.domain.CharacterDetailed
import com.example.rickyandmortyshowcase.domain.CharacterSimple
import kotlinx.coroutines.flow.Flow

class CharactersRepository(
    private val remoteCharactersDataSource: RemoteCharactersDataSource,
    private val favoritesDao: FavoritesDao
) {
    suspend fun getCharacters(): List<CharacterSimple> {
        return remoteCharactersDataSource.getCharacters()
    }

    suspend fun getCharacterDetails(id: String): CharacterDetailed? {
        return remoteCharactersDataSource.getCharacterDetails(id)
    }

    suspend fun getCharactersByName(name: String): List<CharacterSimple> {
        return remoteCharactersDataSource.getCharactersByName(name)
    }

    suspend fun upsertCharacterToFavourites(favourite: Favourite) = favoritesDao.upsertCharacter(favourite)

    suspend fun deleteCharacterFromFavourites(favourite: Favourite) = favoritesDao.deleteCharacter(favourite)

    fun getFavourites(): Flow<List<String>> = favoritesDao.getFavourites()
}