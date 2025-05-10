package com.fomart.rms.core.model

data class CharacterPreview(
    var id: String = "",
    val name: String = "",
    val status: String = "",
    val favorite: Boolean = false,
    val imageUrl: String = ""
)