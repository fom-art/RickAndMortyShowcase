package com.example.rickyandmortyshowcase.database.remote.data.utils

import com.example.CharacterQuery
import com.example.CharactersQuery
import com.example.FilterCharactersByNameQuery
import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterDetailed
import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterSimple

fun FilterCharactersByNameQuery.Result.toCharacterSimple(): CharacterSimple {
    return CharacterSimple(
        id = id ?: "-",
        name = name ?: "-",
        status = status ?: "-",
        image = image ?: "-"
    )
}

fun CharactersQuery.Result.toCharacterSimple(): CharacterSimple {
    return CharacterSimple(
        id = id ?: "-",
        name = name ?: "-",
        status = status ?: "-",
        image = image ?: "-"
    )
}

fun CharacterQuery.Character.toCharacterDetailed(): CharacterDetailed {
    return CharacterDetailed(
        id = id ?: "-",
        name = name ?: "-",
        status = status ?: "-",
        image = image ?: "-",
        species = species ?: "-",
        type = type ?: "-",
        gender = gender ?: "-",
        origin = origin?.name ?: "-",
        location = location?.name ?: "-"
    )
}

