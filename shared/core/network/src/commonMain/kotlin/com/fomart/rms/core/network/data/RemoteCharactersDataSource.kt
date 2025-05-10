package com.fomart.rms.core.network.data

import com.fomart.rms.characters.domain.CharacterDetailed
import com.fomart.rms.characters.domain.CharacterSimple
import com.fomart.rms.core.network.domain.RickAndMortyShowcaseClient

class RemoteCharactersDataSource (private val rickAndMortyShowcaseClient: RickAndMortyShowcaseClient){
    suspend fun getCharacters(): List<CharacterSimple> {
        return rickAndMortyShowcaseClient.getCharacters()
    }

    suspend fun getCharacterDetails(id: String): CharacterDetailed? {
        return rickAndMortyShowcaseClient.getCharacterDetails(id)
    }

    suspend fun getCharactersByName(name: String): List<CharacterSimple> {
        return rickAndMortyShowcaseClient.getCharactersByName(name)
    }
}