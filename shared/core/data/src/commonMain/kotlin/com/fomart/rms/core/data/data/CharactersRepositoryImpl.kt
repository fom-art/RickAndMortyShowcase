package com.fomart.rms.core.data.data

import com.fomart.rms.core.model.domain.Result
import com.fomart.rms.core.model.domain.error.Error
import com.fomart.rms.core.data.domain.repository.CharactersRepository
import com.fomart.rms.core.database.domain.FavoritesDao
import com.fomart.rms.core.model.Character
import com.fomart.rms.core.model.CharacterPreview
import com.fomart.rms.core.model.domain.EmptyResult
import com.fomart.rms.core.data.domain.ApiError
import com.fomart.rms.core.network.domain.CharactersDataSource
import com.fomart.rms.core.network.model.PagedCharactersResult
import io.ktor.client.network.sockets.SocketTimeoutException
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.io.IOException

class CharactersRepositoryImpl(
    private val charactersDataSource: CharactersDataSource,
    private val favoritesDao: FavoritesDao
) : CharactersRepository {
    override suspend fun loadCharactersPreviewsPage(page: Int): Result<PagedCharactersResult, Error> =
        withContext(Dispatchers.IO) {
            try {
                val favorites = favoritesDao.getFavourites().first().toSet()
                val result = charactersDataSource.getCharactersPage(page)
                val updatedCharacters =
                    result.characters.map { it.copy(favorite = favorites.contains(it.id)) }
                Result.Success(result.copy(characters = updatedCharacters))
            } catch (e: SocketTimeoutException) {
                Result.Error(ApiError.Timeout())
            } catch (e: IOException) {
                Result.Error(ApiError.Network(e))
            } catch (e: Exception) {
                Result.Error(Error.Unexpected(e))
            }
        }

    override suspend fun loadCharactersPreviewsPageByName(
        name: String,
        page: Int
    ): Result<PagedCharactersResult, Error> = withContext(Dispatchers.IO) {
        try {
            val favorites = favoritesDao.getFavourites().first().toSet()
            val result = charactersDataSource.getCharactersByName(name, page)
            val updatedCharacters =
                result.characters.map { it.copy(favorite = favorites.contains(it.id)) }
            Result.Success(result.copy(characters = updatedCharacters))
        } catch (e: SocketTimeoutException) {
            Result.Error(ApiError.Timeout())
        } catch (e: IOException) {
            Result.Error(ApiError.Network(e))
        } catch (e: Exception) {
            Result.Error(Error.Unexpected(e))
        }
    }

    override suspend fun getCharacterById(id: String): Result<Character, Error> =
        withContext(Dispatchers.IO) {
            try {
                val favorites = favoritesDao.getFavourites().first().toSet()
                val character = charactersDataSource.getCharacterDetails(id)
                character?.let {
                    val isFavorite = favorites.contains(it.id)
                    val updated = it.copy(base = it.base.copy(favorite = isFavorite))
                    Result.Success(updated)
                } ?: Result.Error(ApiError.NotFound)
            } catch (e: SocketTimeoutException) {
                Result.Error(ApiError.Timeout())
            } catch (e: IOException) {
                Result.Error(ApiError.Network(e))
            } catch (e: Exception) {
                Result.Error(Error.Unexpected(e))
            }
        }

    override suspend fun getFavoriteCharactersPreviews(): Result<List<CharacterPreview>, Error> =
        withContext(Dispatchers.IO) {
            try {
                val favoriteIds = favoritesDao.getFavourites().first()
                val characters = charactersDataSource.getCharactersByIds(favoriteIds)
                val updatedCharacters = characters.map { it.copy(favorite = true) }
                Result.Success(updatedCharacters)
            } catch (e: SocketTimeoutException) {
                Result.Error(ApiError.Timeout())
            } catch (e: IOException) {
                Result.Error(ApiError.Network(e))
            } catch (e: Exception) {
                Result.Error(Error.Unexpected(e))
            }
        }

    override suspend fun upsertCharacterToFavouritesById(id: String): EmptyResult<Error> =
        withContext(Dispatchers.IO) {
            try {
                favoritesDao.upsertCharacterId(id)
                Result.Success(Unit)
            } catch (e: Exception) {
                Result.Error(Error.Unexpected(e))
            }
        }

    override suspend fun deleteCharacterFromFavouritesById(id: String): EmptyResult<Error> =
        withContext(Dispatchers.IO) {
            try {
                favoritesDao.deleteCharacterId(id)
                Result.Success(Unit)
            } catch (e: Exception) {
                Result.Error(Error.Unexpected(e))
            }
        }
}