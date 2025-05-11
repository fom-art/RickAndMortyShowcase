package com.fomart.rms.shared.feature.character_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fomart.rms.core.data.domain.repository.CharactersRepository
import com.fomart.rms.core.model.Character
import com.fomart.rms.core.model.domain.Result
import com.fomart.rms.core.model.domain.error.Error
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    private val charactersRepository: CharactersRepository,
    private val characterId: String
) : ViewModel() {
    private val _character: MutableStateFlow<Character?> = MutableStateFlow(null)
    val character = _character.asStateFlow()

    init {
        viewModelScope.launch {
            charactersRepository.getCharacterById(characterId).collect { result ->
                when (result) {
                    is Result.Success<Character> -> _character.update { result.data }
                    is Result.Error<Error> -> processError(result.error)
                }
            }
        }
    }

    fun toggleFavorite() {
        val character = _character.value
        if (character == null) return
        viewModelScope.launch {
            if (character.favorite) {
                charactersRepository.deleteCharacterFromFavouritesById(characterId)
            } else {
                charactersRepository.upsertCharacterToFavouritesById(characterId)
            }
        }
    }

    private fun processError(error: Error) {

    }
}