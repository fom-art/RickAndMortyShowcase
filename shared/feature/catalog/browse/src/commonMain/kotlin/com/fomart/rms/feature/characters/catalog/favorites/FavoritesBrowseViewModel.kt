package com.fomart.rms.feature.characters.catalog.favorites

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fomart.rms.core.data.domain.repository.CharactersRepository
import com.fomart.rms.core.model.CharacterPreview

class FavoritesBrowseViewModel(
    private val charactersRepository: CharactersRepository
) : ViewModel() {
    var charactersPreviews: List<CharacterPreview> by mutableStateOf(emptyList())
        private set


}