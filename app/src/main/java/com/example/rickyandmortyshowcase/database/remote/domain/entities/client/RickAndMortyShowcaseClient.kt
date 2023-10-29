package com.example.rickyandmortyshowcase.database.remote.domain.entities.client

import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterDetailed
import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterSimple

interface RickAndMortyShowcaseClient {
    suspend fun getCharacters(): List<CharacterSimple>
    suspend fun getCharacterDetails(id: String): CharacterDetailed?
    suspend fun getCharactersByName(name: String): List<CharacterSimple>
}