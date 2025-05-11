package com.fomart.rms.shared.feature.catalog.all.browse.domain

import com.fomart.rms.core.model.CharacterPreview

data class BrowseState(
    val charactersPreviews: List<CharacterPreview> = emptyList(),
    val lastPageLoaded: Int = 0,
    val totalPages: Int = 0
) {
    val isLastPageLoaded: Boolean get() = lastPageLoaded >= totalPages
}