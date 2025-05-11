package com.fomart.rms.core.network.model

import com.fomart.rms.core.model.CharacterPreview

data class PagedCharactersResult(
    val charactersPreviews: List<CharacterPreview>,
    val currentPage: Int,
    val totalPages: Int
)