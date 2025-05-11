package com.fomart.rms.core.data.data

import com.fomart.rms.core.data.domain.ApiError
import com.fomart.rms.core.data.domain.repository.CharactersRepository
import com.fomart.rms.core.database.domain.FavoritesDao
import com.fomart.rms.core.model.Character
import com.fomart.rms.core.model.CharacterPreview
import com.fomart.rms.core.model.domain.EmptyResult
import com.fomart.rms.core.model.domain.Result
import com.fomart.rms.core.model.domain.error.Error
import com.fomart.rms.core.network.domain.CharactersDataSource
import com.fomart.rms.core.network.model.PagedCharactersResult
import io.ktor.client.network.sockets.SocketTimeoutException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.withContext
import kotlinx.io.IOException

class CharactersRepositoryImpl(
    private val charactersDataSource: CharactersDataSource,
    private val favoritesDao: FavoritesDao
) : CharactersRepository {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun loadCharactersPreviewsPage(page: Int): Flow<Result<PagedCharactersResult, Error>> =
        favoritesDao.getFavourites().mapLatest { favorites ->
            try {
                val result = charactersDataSource.getCharactersPage(page)
                val updatedCharacters =
                    result.charactersPreviews.map { it.copy(favorite = favorites.contains(it.id)) }
                Result.Success(result.copy(charactersPreviews = updatedCharacters))
            } catch (e: SocketTimeoutException) {
                Result.Error(ApiError.Timeout())
            } catch (e: IOException) {
                Result.Error(ApiError.Network(e))
            } catch (e: Exception) {
                Result.Error(Error.Unexpected(e))
            }
        }.flowOn(Dispatchers.IO)


    @OptIn(ExperimentalCoroutinesApi::class)
    override fun loadCharactersPreviewsPageByName(
        name: String,
        page: Int
    ): Flow<Result<PagedCharactersResult, Error>> =
        favoritesDao.getFavourites().mapLatest { favorites ->
            try {
                val result = charactersDataSource.getCharactersByName(name, page)
                val updatedCharacters = result.charactersPreviews.map {
                    it.copy(favorite = favorites.contains(it.id))
                }
                Result.Success(result.copy(charactersPreviews = updatedCharacters))
            } catch (e: SocketTimeoutException) {
                Result.Error(ApiError.Timeout())
            } catch (e: IOException) {
                Result.Error(ApiError.Network(e))
            } catch (e: Exception) {
                Result.Error(Error.Unexpected(e))
            }
        }.flowOn(Dispatchers.IO)


    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getCharacterById(id: String): Flow<Result<Character, Error>> =
        favoritesDao.getFavourites().mapLatest { favorites ->
            try {
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
        }.flowOn(Dispatchers.IO)


    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getFavoriteCharactersPreviews(): Flow<Result<List<CharacterPreview>, Error>> =
        favoritesDao.getFavourites().mapLatest { favoriteIds ->
            try {
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
        }.flowOn(Dispatchers.IO)


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