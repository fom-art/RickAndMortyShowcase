package com.fomart.rms.feature.characters.catalog.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fomart.rms.core.model.Character

class CharacterDetailsViewModel(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
    private val upsertCharacterToFavouritesUseCase: UpsertCharacterToFavouritesUseCase,
    private val deleteCharacterFromFavouritesUseCase: DeleteCharacterFromFavouritesUseCase
): ViewModel() {
    var character by mutableStateOf(Character())

    fun toggleFavorite() {

    }
}