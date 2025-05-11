package com.fomart.rms.core.model

data class PagedCharactersResult(
    val charactersPreviews: List<CharacterPreview>,
    val currentPage: Int,
    val totalPages: Int
)