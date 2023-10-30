package com.example.rickyandmortyshowcase.database.remote.domain.entities

import android.graphics.Bitmap

data class CharacterDetailed(
    val id: String,
    val name: String,
    val status: String,
    val image: Bitmap,
    val species: String,
    val type: String,
    val gender: String,
    val origin: String,
    val location: String
)
