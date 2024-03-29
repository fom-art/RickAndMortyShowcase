package com.example.rickyandmortyshowcase.data.network

import com.example.rickyandmortyshowcase.domain.CharacterDetailed
import com.example.rickyandmortyshowcase.domain.CharacterSimple

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