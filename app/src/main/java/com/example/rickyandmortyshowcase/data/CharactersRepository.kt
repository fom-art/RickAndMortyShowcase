package com.example.rickyandmortyshowcase.data

import com.example.rickyandmortyshowcase.data.client.RickAndMortyShowcaseClient
import com.example.rickyandmortyshowcase.data.model.CharacterDetailed
import com.example.rickyandmortyshowcase.data.model.CharacterSimple

//class CharactersRepository(private val rickAndMortyShowcaseClient: RickAndMortyShowcaseClient) {
//    suspend fun getCharacters(): List<CharacterSimple> {
//        return rickAndMortyShowcaseClient.getCharacters()
//    }
//
//    suspend fun getCharacterDetails(id: String): CharacterDetailed? {
//        return rickAndMortyShowcaseClient.getCharacterDetails(id)
//    }
//
//    suspend fun getCharactersByName(name: String): List<CharacterSimple> {
//        return rickAndMortyShowcaseClient.getCharactersByName(name)
//    }
//}


class CharactersRepository(private val remoteCharactersDataSource: RemoteCharactersDataSource) {
    suspend fun getCharacters(): List<CharacterSimple> {
        return remoteCharactersDataSource.getCharacters()
    }

    suspend fun getCharacterDetails(id: String): CharacterDetailed? {
        return remoteCharactersDataSource.getCharacterDetails(id)
    }

    suspend fun getCharactersByName(name: String): List<CharacterSimple> {
        return remoteCharactersDataSource.getCharactersByName(name)
    }
}