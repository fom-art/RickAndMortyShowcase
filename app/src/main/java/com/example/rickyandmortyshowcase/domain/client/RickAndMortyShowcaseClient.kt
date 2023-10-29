package com.example.rickyandmortyshowcase.domain.client

import com.example.rickyandmortyshowcase.domain.entities.CharacterDetailed
import com.example.rickyandmortyshowcase.domain.entities.CharacterSimple

interface RickAndMortyShowcaseClient {
    suspend fun getCharacters(): List<CharacterSimple>
    suspend fun getCharacterDetails(id: String): CharacterDetailed?
    suspend fun getCharactersByName(name: String): List<CharacterSimple>
}