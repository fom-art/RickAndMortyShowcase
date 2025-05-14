package com.fomart.rms.shared.feature.catalog.domain

import com.fomart.rms.core.model.CharacterPreview

data class CatalogState(
    val charactersPreviewsRaw: List<CharacterPreview> = emptyList(),
    val favoriteCharacterIds: Set<String> = emptySet(),
    val selectedCharacter: CharacterPreview? = null,
    val lastPageLoaded: Int = 0,
    val totalPages: Int = 0
) {
    val isLastPageLoaded: Boolean get() = lastPageLoaded >= totalPages
    val charactersPreviews: List<CharacterPreview>
        get() = charactersPreviewsRaw.map { preview ->
            preview.copy(favorite = preview.id in favoriteCharacterIds)
        }
}