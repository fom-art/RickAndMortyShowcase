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

class ApolloRickAndMortyShowcaseClient(
    private val rickAndMortyShowcaseClient: ApolloClient
) : RickAndMortyShowcaseClient {
    override suspend fun getCharacters(): List<CharacterSimple> {
        var result = emptyList<CharacterSimple>()
        val pagesAmount = rickAndMortyShowcaseClient.query(CharactersQuery(1))
            .execute().data!!.characters!!.info!!.pages ?: 0
        var currentPage = 1
        while (currentPage in 1..pagesAmount) {
            result = result + (rickAndMortyShowcaseClient
                .query(CharactersQuery(currentPage))
                .execute()
                .data
                ?.characters
                ?.results
                ?.map { it!!.toCharacterSimple() }
                ?: emptyList())
            currentPage++
        }
        return result
    }

    override suspend fun getCharacterDetails(id: String): CharacterDetailed? {
        return rickAndMortyShowcaseClient.query(CharacterQuery(id))
            .execute()
            .data
            ?.character
            ?.toCharacterDetailed()
    }

    override suspend fun getCharactersByName(name: String): List<CharacterSimple> {
        var result = emptyList<CharacterSimple>()
        val pagesAmount = rickAndMortyShowcaseClient.query(FilterCharactersByNameQuery(page = 1, character_name = name))
            .execute().data!!.characters!!.info!!.pages ?: 0
        var currentPage = 1
        while (currentPage in 1..pagesAmount) {
            result = result + (rickAndMortyShowcaseClient.query(FilterCharactersByNameQuery(character_name = name, page = currentPage))
                .execute()
                .data
                ?.characters
                ?.results
                ?.map { it!!.toCharacterSimple() }
                ?: emptyList())
            currentPage++
        }
        return result
    }
}