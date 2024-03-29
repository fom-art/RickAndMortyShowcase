package com.example.rickyandmortyshowcase.domain

import android.graphics.Bitmap
import java.net.URL

data class CharacterSimple(
    val id: String,
    val name: String,
    val status: String,
    val imageUrl: String
)
