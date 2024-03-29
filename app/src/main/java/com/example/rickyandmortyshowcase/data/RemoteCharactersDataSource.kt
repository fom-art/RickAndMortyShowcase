package com.example.rickyandmortyshowcase.data

import com.example.rickyandmortyshowcase.data.client.RickAndMortyShowcaseClient
import com.example.rickyandmortyshowcase.data.model.CharacterDetailed
import com.example.rickyandmortyshowcase.data.model.CharacterSimple

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