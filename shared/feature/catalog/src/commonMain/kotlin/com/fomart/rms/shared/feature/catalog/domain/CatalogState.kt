package com.fomart.rms.shared.feature.catalog.domain

import com.fomart.rms.core.model.CharacterPreview

data class CatalogState(
    val charactersPreviews: List<CharacterPreview> = emptyList(),
    val selectedCharacter: CharacterPreview? = null,
    val lastPageLoaded: Int = 0,
    val totalPages: Int = 0
) {
    val isLastPageLoaded: Boolean get() = lastPageLoaded >= totalPages
}