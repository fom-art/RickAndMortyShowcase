package com.fomart.rms.core.data.domain.repository

import com.fomart.rms.core.model.domain.EmptyResult
import com.fomart.rms.core.model.domain.Result
import com.fomart.rms.core.model.Character
import com.fomart.rms.core.model.domain.error.Error
import com.fomart.rms.core.model.CharacterPreview
import com.fomart.rms.core.model.PagedCharactersResult
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun loadCharactersPreviewsPage(page: Int): Flow<Result<PagedCharactersResult, Error>>
    fun loadCharactersPreviewsPageByName(name: String, page: Int): Flow<Result<PagedCharactersResult, Error>>
    fun getCharacterById(id: String): Flow<Result<Character, Error>>
    fun getFavoriteCharactersPreviews(): Flow<Result<List<CharacterPreview>, Error>>
    suspend fun upsertCharacterToFavouritesById(id: String): EmptyResult<Error>
    suspend fun deleteCharacterFromFavouritesById(id: String): EmptyResult<Error>
}