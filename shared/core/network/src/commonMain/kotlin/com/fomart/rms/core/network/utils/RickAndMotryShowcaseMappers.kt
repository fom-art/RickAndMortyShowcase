package com.fomart.rms.core.network.utils

import com.example.CharacterQuery
import com.example.CharactersQuery
import com.example.FilterCharactersByNameQuery
import com.fomart.rms.characters.domain.CharacterDetailed
import com.fomart.rms.characters.domain.CharacterSimple

fun FilterCharactersByNameQuery.Result.toCharacterSimple(): CharacterSimple {
    return CharacterSimple(
        id = id ?: "-",
        name = name ?: "-",
        status = status ?: "-",
        imageUrl = image ?: "-"
    )
}

fun CharactersQuery.Result.toCharacterSimple(): CharacterSimple {
    return CharacterSimple(
        id = id ?: "-",
        name = name ?: "-",
        status = status ?: "-",
        imageUrl = image ?: "-"
    )
}

fun CharacterQuery.Character.toCharacterDetailed(): CharacterDetailed {
    return CharacterDetailed(
        id = id ?: "-",
        name = name ?: "-",
        status = status ?: "-",
        imageUrl = image ?: "-",
        species = species ?: "-",
        type = if (type.isNullOrBlank()) "-" else type,
        gender = gender ?: "-",
        origin = origin?.name ?: "-",
        location = location?.name ?: "-"
    )
}

