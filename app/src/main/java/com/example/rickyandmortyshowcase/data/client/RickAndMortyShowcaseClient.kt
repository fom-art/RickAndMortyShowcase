package com.example.rickyandmortyshowcase.data.client

import com.example.rickyandmortyshowcase.data.model.CharacterDetailed
import com.example.rickyandmortyshowcase.data.model.CharacterSimple

interface RickAndMortyShowcaseClient {
    suspend fun getCharacters(): List<CharacterSimple>
    suspend fun getCharacterDetails(id: String): CharacterDetailed?
    suspend fun getCharactersByName(name: String): List<CharacterSimple>
}