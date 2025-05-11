package com.fomart.rms.shared.feature.catalog.all.browse.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fomart.rms.core.data.domain.repository.CharactersRepository
import com.fomart.rms.core.model.CharacterPreview
import com.fomart.rms.core.model.domain.Result
import com.fomart.rms.core.model.domain.error.Error
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val charactersRepository: CharactersRepository
) : ViewModel() {
    private val _favoriteCharactersPreviews = MutableStateFlow<List<CharacterPreview>>(emptyList())
    val favoriteCharactersPreviews = _favoriteCharactersPreviews.asStateFlow()

    init {
        viewModelScope.launch {
            charactersRepository.getFavoriteCharactersPreviews().collect { result ->
                when(result) {
                    is Result.Success<List<CharacterPreview>> -> _favoriteCharactersPreviews.update { result.data }
                    is Result.Error<Error> -> processError(result.error)
                }
            }
        }
    }

    fun processError(error: Error) {

    }
}