package com.fomart.rms.core.network.utils

import com.fomart.rms.core.model.Character
import com.fomart.rms.core.model.CharacterPreview
import com.fomart.rms.core.network.graphql.CharacterQuery
import com.fomart.rms.core.network.graphql.CharactersByIdsQuery
import com.fomart.rms.core.network.graphql.CharactersQuery
import com.fomart.rms.core.network.graphql.FilterCharactersByNameQuery

fun CharactersQuery.Result.toCharacterPreview(): CharacterPreview {
    return CharacterPreview(
        id = id ?: "-",
        name = name ?: "-",
        status = status ?: "-",
        imageUrl = image ?: "-"
    )
}

fun FilterCharactersByNameQuery.Result.toCharacterPreview(): CharacterPreview {
    return CharacterPreview(
        id = id ?: "-",
        name = name ?: "-",
        status = status ?: "-",
        imageUrl = image ?: "-"
    )
}

fun CharacterQuery.Character.toCharacter(): Character {
    return Character(
        base = CharacterPreview(
            id = id ?: "-",
            name = name ?: "-",
            status = status ?: "-",
            imageUrl = image ?: "-"
        ),
        species = species ?: "-",
        type = if (type.isNullOrBlank()) "-" else type,
        gender = gender ?: "-",
        origin = origin?.name ?: "-",
        location = location?.name ?: "-"
    )
}
fun CharactersByIdsQuery.CharactersById.toCharacterPreview(): CharacterPreview {
    return CharacterPreview(
        id = id ?: "-",
        name = name ?: "-",
        status = status ?: "-",
        imageUrl = image ?: "-"
    )
}
