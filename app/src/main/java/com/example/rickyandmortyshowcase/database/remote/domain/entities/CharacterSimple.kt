package com.example.rickyandmortyshowcase.database.remote.domain.entities

import android.graphics.Bitmap

data class CharacterSimple(
    val id: String,
    val name: String,
    val status: String,
    val image: Bitmap
)
