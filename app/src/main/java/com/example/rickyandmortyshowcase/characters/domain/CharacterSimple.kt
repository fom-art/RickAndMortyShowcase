package com.example.rickyandmortyshowcase.characters.domain

import android.graphics.Bitmap
import java.net.URL

data class CharacterSimple(
    var id: String,
    val name: String,
    val status: String,
    val imageUrl: String
)
