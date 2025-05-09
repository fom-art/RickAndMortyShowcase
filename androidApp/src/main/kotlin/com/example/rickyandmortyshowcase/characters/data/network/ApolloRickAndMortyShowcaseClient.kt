package com.example.rickyandmortyshowcase.characters.data.network

import com.apollographql.apollo3.ApolloClient
import com.example.CharacterQuery
import com.example.CharactersQuery
import com.example.FilterCharactersByNameQuery
import com.example.rickyandmortyshowcase.characters.domain.CharacterDetailed
import com.example.rickyandmortyshowcase.characters.domain.CharacterSimple

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