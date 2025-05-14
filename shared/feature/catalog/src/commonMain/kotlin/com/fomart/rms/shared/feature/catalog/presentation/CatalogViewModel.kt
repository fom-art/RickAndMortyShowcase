package com.fomart.rms.shared.feature.catalog.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fomart.rms.core.data.domain.repository.CharactersRepository
import com.fomart.rms.core.model.CharacterPreview
import com.fomart.rms.core.model.PagedCharactersResult
import com.fomart.rms.core.model.domain.Result
import com.fomart.rms.core.model.domain.error.Error
import com.fomart.rms.shared.feature.catalog.domain.CatalogState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CatalogViewModel(
    private val charactersRepository: CharactersRepository
) : ViewModel() {
    private val _catalogState = MutableStateFlow(CatalogState())
    val catalogState = _catalogState.asStateFlow()

    init {
        viewModelScope.launch {
            loadPage(1)
        }

        viewModelScope.launch {
            charactersRepository.getFavoriteCharactersPreviews().collect { result ->
                if (result is Result.Success) {
                    val favoriteIds = result.data.map { it.id }.toSet()
                    _catalogState.update {
                        it.copy(favoriteCharacterIds = favoriteIds)
                    }
                }
            }
        }
    }


    fun loadNextPage() {
        val state = _catalogState.value
        if (!state.isLastPageLoaded) {
            loadPage(state.lastPageLoaded + 1)
        }
    }

    fun selectCharacter(character: CharacterPreview) {
        _catalogState.update {
            it.copy(
                selectedCharacter = character
            )
        }
    }

    private fun loadPage(page: Int) {
        viewModelScope.launch {
            charactersRepository.loadCharactersPreviewsPage(page = page).collect { result ->
                when(result) {
                    is Result.Success<PagedCharactersResult> -> processResult(result.data)
                    is Result.Error<Error> -> processError(result.error)
                }
            }
        }
    }
    private fun processResult(pagedCharactersResult: PagedCharactersResult) {
        _catalogState.update {
            val currentPreviews = it.charactersPreviews
            val updatedPreviews = currentPreviews + pagedCharactersResult.charactersPreviews
            it.copy(
                charactersPreviewsRaw = updatedPreviews,
                lastPageLoaded = pagedCharactersResult.currentPage,
                totalPages = pagedCharactersResult.totalPages
            )
        }
    }

    private fun processError(error: Error) {

    }
}