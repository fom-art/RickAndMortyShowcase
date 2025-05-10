package com.fomart.rms.core.data.domain.repository

import com.fomart.mafiamaster.core.utils.domain.EmptyResult
import com.fomart.mafiamaster.core.utils.domain.Result
import com.fomart.rms.core.model.Character
import com.fomart.mafiamaster.core.utils.domain.error.Error
import com.fomart.rms.core.model.CharacterPreview

interface CharactersRepository {
    suspend fun getCharactersPreviews(): Result<List<CharacterPreview>, Error>

    suspend fun getCharacterDetailedById(id: String): Result<Character, Error>

    suspend fun getCharactersPreviewsByName(name: String): Result<List<CharacterPreview>, Error>

    suspend fun getFavoriteCharactersPreview(): Result<List<CharacterPreview>, Error>

    suspend fun upsertCharacterToFavourites(character: Character): EmptyResult<Error>

    suspend fun deleteCharacterFromFavourites(character: Character): EmptyResult<Error>
}