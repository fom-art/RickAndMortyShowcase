package com.fomart.rms.core.network.domain

import com.fomart.rms.core.model.Character
import com.fomart.rms.core.model.CharacterPreview
import com.fomart.rms.core.network.model.PagedCharactersResult

interface RickAndMortyShowcaseClient {
    suspend fun getCharactersPage(page: Int): PagedCharactersResult
    suspend fun getCharacterDetails(id: String): Character?
    suspend fun getCharactersByIds(ids: List<String>): List<CharacterPreview>
    suspend fun getCharactersByName(name: String, page: Int): PagedCharactersResult
}