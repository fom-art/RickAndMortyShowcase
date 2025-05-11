package com.fomart.rms.core.data.domain.repository

import com.fomart.rms.core.model.domain.EmptyResult
import com.fomart.rms.core.model.domain.Result
import com.fomart.rms.core.model.Character
import com.fomart.rms.core.model.domain.error.Error
import com.fomart.rms.core.model.CharacterPreview
import com.fomart.rms.core.network.model.PagedCharactersResult

interface CharactersRepository {
    suspend fun loadCharactersPreviewsPage(page: Int): Result<PagedCharactersResult, Error>
    suspend fun loadCharactersPreviewsPageByName(name: String, page: Int): Result<PagedCharactersResult, Error>
    suspend fun getCharacterById(id: String): Result<Character, Error>
    suspend fun getFavoriteCharactersPreviews(): Result<List<CharacterPreview>, Error>
    suspend fun upsertCharacterToFavouritesById(id: String): EmptyResult<Error>
    suspend fun deleteCharacterFromFavouritesById(id: String): EmptyResult<Error>
}