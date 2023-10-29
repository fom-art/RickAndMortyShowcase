package com.example.rickyandmortyshowcase.database.remote.data.client

import com.apollographql.apollo3.ApolloClient
import com.example.CharacterQuery
import com.example.CharactersQuery
import com.example.FilterCharactersByNameQuery
import com.example.rickyandmortyshowcase.database.remote.data.utils.toCharacterDetailed
import com.example.rickyandmortyshowcase.database.remote.data.utils.toCharacterSimple
import com.example.rickyandmortyshowcase.database.remote.domain.entities.client.RickAndMortyShowcaseClient
import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterDetailed
import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterSimple

class RickAndMortyShowcaseClient(private val rickAndMortyShowcaseClient: ApolloClient) :
    RickAndMortyShowcaseClient {
    override suspend fun getCharacters(): List<CharacterSimple> {
        return rickAndMortyShowcaseClient.query(CharactersQuery())
            .execute()
            .data
            ?.characters
            ?.results
            ?.map { it!!.toCharacterSimple() }
            ?: emptyList()
    }

    override suspend fun getCharacterDetails(id: String): CharacterDetailed? {
        return rickAndMortyShowcaseClient.query(CharacterQuery(id))
            .execute()
            .data
            ?.character
            ?.toCharacterDetailed()

    }

    override suspend fun getCharactersByName(name: String): List<CharacterSimple> {
        return rickAndMortyShowcaseClient.query(FilterCharactersByNameQuery(name))
            .execute()
            .data
            ?.characters
            ?.results
            ?.map { it!!.toCharacterSimple() }
            ?: emptyList()
    }

}