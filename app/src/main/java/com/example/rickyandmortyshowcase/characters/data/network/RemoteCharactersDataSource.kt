package com.example.rickyandmortyshowcase.characters.data.network

import com.example.rickyandmortyshowcase.characters.domain.CharacterDetailed
import com.example.rickyandmortyshowcase.characters.domain.CharacterSimple

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