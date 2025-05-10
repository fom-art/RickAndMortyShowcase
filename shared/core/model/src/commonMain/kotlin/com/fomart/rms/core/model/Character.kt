package com.fomart.rms.core.model

data class Character(
    val base: CharacterPreview = CharacterPreview(),
    val species: String = "",
    val type: String = "",
    val gender: String = "",
    val origin: String = "",
    val location: String = ""
) {
    val id: String get() = base.id
    val name: String get() = base.name
    val status: String get() = base.status
    val favorite: Boolean get() = base.favorite
    val imageUrl: String get() = base.imageUrl
}
