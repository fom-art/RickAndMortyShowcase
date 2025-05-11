package com.fomart.rms.core.network.data

import com.fomart.rms.core.model.Character
import com.fomart.rms.core.network.domain.CharactersDataSource
import com.fomart.rms.core.network.model.PagedCharactersResult

class RemoteCharactersDataSource(
    private val client: CharactersDataSource
) {
    suspend fun getCharactersPage(page: Int): PagedCharactersResult = client.getCharactersPage(page)
    suspend fun getCharacterDetails(id: String): Character? = client.getCharacterDetails(id)
    suspend fun getCharactersByName(name: String, page: Int): PagedCharactersResult = client.getCharactersByName(name, page)
}
