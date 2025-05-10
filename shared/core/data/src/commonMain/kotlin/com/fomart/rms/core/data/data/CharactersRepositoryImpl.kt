package com.fomart.rms.core.data.data

import com.fomart.mafiamaster.core.utils.domain.EmptyResult
import com.fomart.mafiamaster.core.utils.domain.Result
import com.fomart.mafiamaster.core.utils.domain.error.Error
import com.fomart.rms.core.data.domain.repository.CharactersRepository
import com.fomart.rms.core.model.Character
import com.fomart.rms.core.model.CharacterPreview

class CharactersRepositoryImpl(

): CharactersRepository {
    override suspend fun getCharactersPreviews(): Result<List<CharacterPreview>, Error> {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterDetailedById(id: String): Result<Character, Error> {
        TODO("Not yet implemented")
    }

    override suspend fun getCharactersPreviewsByName(name: String): Result<List<CharacterPreview>, Error> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavoriteCharactersPreview(): Result<List<CharacterPreview>, Error> {
        TODO("Not yet implemented")
    }

    override suspend fun upsertCharacterToFavourites(character: Character): EmptyResult<Error> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCharacterFromFavourites(character: Character): EmptyResult<Error> {
        TODO("Not yet implemented")
    }
}