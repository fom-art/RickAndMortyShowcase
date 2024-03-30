package com.example.rickyandmortyshowcase.characters.domain

import android.graphics.Bitmap

data class CharacterDetailed(
    val id: String,
    val name: String,
    val status: String,
    val imageUrl: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: String,
    val location: String
)
