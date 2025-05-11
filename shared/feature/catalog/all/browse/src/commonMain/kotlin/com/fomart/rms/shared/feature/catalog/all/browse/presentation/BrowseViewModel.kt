package com.fomart.rms.shared.feature.catalog.all.browse.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fomart.rms.core.data.domain.repository.CharactersRepository
import com.fomart.rms.core.model.domain.Result
import com.fomart.rms.core.model.domain.error.Error
import com.fomart.rms.core.model.PagedCharactersResult
import com.fomart.rms.shared.feature.catalog.all.browse.domain.BrowseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BrowseViewModel(
    private val charactersRepository: CharactersRepository
) : ViewModel() {
    private val _browseState = MutableStateFlow(BrowseState())
    val browseState = _browseState.asStateFlow()

    init {
        viewModelScope.launch {
            loadPage(1)
        }
    }

    fun loadNextPage() {
        val state = _browseState.value
        if (!state.isLastPageLoaded) {
            loadPage(state.lastPageLoaded + 1)
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
        _browseState.update {
            val currentPreviews = it.charactersPreviews
            val updatedPreviews = currentPreviews + pagedCharactersResult.charactersPreviews
            it.copy(
                charactersPreviews = updatedPreviews,
                lastPageLoaded = pagedCharactersResult.currentPage,
                totalPages = pagedCharactersResult.totalPages
            )
        }
    }

    private fun processError(error: Error) {

    }
}