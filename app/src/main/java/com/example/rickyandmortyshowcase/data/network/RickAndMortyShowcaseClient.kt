package com.example.rickyandmortyshowcase.data.network

import com.example.rickyandmortyshowcase.domain.CharacterDetailed
import com.example.rickyandmortyshowcase.domain.CharacterSimple

interface RickAndMortyShowcaseClient {
    suspend fun getCharacters(): List<CharacterSimple>
    suspend fun getCharacterDetails(id: String): CharacterDetailed?
    suspend fun getCharactersByName(name: String): List<CharacterSimple>
}