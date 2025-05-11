package com.fomart.rms.core.network.data

import com.apollographql.apollo.ApolloClient
import com.fomart.rms.core.model.Character
import com.fomart.rms.core.model.CharacterPreview
import com.fomart.rms.core.network.domain.RickAndMortyShowcaseClient
import com.fomart.rms.core.network.graphql.CharacterQuery
import com.fomart.rms.core.network.graphql.CharactersByIdsQuery
import com.fomart.rms.core.network.graphql.CharactersQuery
import com.fomart.rms.core.network.graphql.FilterCharactersByNameQuery
import com.fomart.rms.core.network.model.PagedCharactersResult
import com.fomart.rms.core.network.utils.toCharacter
import com.fomart.rms.core.network.utils.toCharacterPreview

class ApolloRickAndMortyShowcaseClient(
    private val apolloClient: ApolloClient
) : RickAndMortyShowcaseClient {

    override suspend fun getCharactersPage(page: Int): PagedCharactersResult {
        val response = apolloClient.query(CharactersQuery(page)).execute()
        val characters = response.data?.characters?.results?.mapNotNull {
            it?.toCharacterPreview()
        } ?: emptyList()

        val totalPages = response.data?.characters?.info?.pages ?: 0

        return PagedCharactersResult(
            characters = characters,
            currentPage = page,
            totalPages = totalPages
        )
    }

    override suspend fun getCharacterDetails(id: String): Character? {
        return apolloClient.query(CharacterQuery(id))
            .execute()
            .data
            ?.character
            ?.toCharacter()
    }


    override suspend fun getCharactersByIds(ids: List<String>): List<CharacterPreview> {
        val response = apolloClient.query(CharactersByIdsQuery(ids)).execute()
        return response.data?.charactersByIds?.mapNotNull {
            it?.toCharacterPreview()
        } ?: emptyList()
    }

    override suspend fun getCharactersByName(name: String, page: Int): PagedCharactersResult {
        val response = apolloClient.query(FilterCharactersByNameQuery(name, page)).execute()
        val characters = response.data?.characters?.results?.mapNotNull {
            it?.toCharacterPreview()
        } ?: emptyList()

        val totalPages = response.data?.characters?.info?.pages ?: 0

        return PagedCharactersResult(
            characters = characters,
            currentPage = page,
            totalPages = totalPages
        )
    }
}