package com.example.rickyandmortyshowcase.database.remote.data.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import com.example.CharacterQuery
import com.example.CharactersQuery
import com.example.FilterCharactersByNameQuery
import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterDetailed
import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterSimple
import java.net.URL

fun FilterCharactersByNameQuery.Result.toCharacterSimple(): CharacterSimple {
    val url = URL(image)
    return CharacterSimple(
        id = id ?: "-",
        name = name ?: "-",
        status = status ?: "-",
        image = BitmapFactory.decodeStream(url.openConnection().getInputStream()) ?: Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)

    )
}

fun CharactersQuery.Result.toCharacterSimple(): CharacterSimple {
    val url = URL(image)
    return CharacterSimple(
        id = id ?: "-",
        name = name ?: "-",
        status = status ?: "-",
        image = BitmapFactory.decodeStream(url.openConnection().getInputStream()) ?: Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
    )
}

fun CharacterQuery.Character.toCharacterDetailed(): CharacterDetailed {
    val url = URL(image)
    return CharacterDetailed(
        id = id ?: "-",
        name = name ?: "-",
        status = status ?: "-",
        image = BitmapFactory.decodeStream(url.openConnection().getInputStream()) ?: Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888),
        species = species ?: "-",
        type = type ?: "-",
        gender = gender ?: "-",
        origin = origin?.name ?: "-",
        location = location?.name ?: "-"
    )
}

