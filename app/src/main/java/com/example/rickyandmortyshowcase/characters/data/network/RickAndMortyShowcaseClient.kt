package com.example.rickyandmortyshowcase.characters.data.network

import com.example.rickyandmortyshowcase.characters.domain.CharacterDetailed
import com.example.rickyandmortyshowcase.characters.domain.CharacterSimple

interface RickAndMortyShowcaseClient {
    suspend fun getCharacters(): List<CharacterSimple>
    suspend fun getCharacterDetails(id: String): CharacterDetailed?
    suspend fun getCharactersByName(name: String): List<CharacterSimple>
}