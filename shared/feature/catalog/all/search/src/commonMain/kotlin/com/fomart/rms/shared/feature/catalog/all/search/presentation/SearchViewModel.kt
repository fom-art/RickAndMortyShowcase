package com.fomart.rms.shared.feature.catalog.all.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fomart.rms.core.data.domain.repository.CharactersRepository
import com.fomart.rms.core.model.domain.Result
import com.fomart.rms.core.model.domain.error.Error
import com.fomart.rms.core.model.PagedCharactersResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val charactersRepository: CharactersRepository
) : ViewModel() {
    private val _searchBrowseState = MutableStateFlow(SearchState())
    val searchBrowseState = _searchBrowseState.asStateFlow()

    fun updateNameFilter(name: String) {
        if (name.isEmpty()) {
            clean()
        } else {
            loadPageWithName(1, name)
        }
    }

    fun loadNextPage() {
        val state = _searchBrowseState.value
        if (!state.isLastPageLoaded) {
            loadPageWithName(state.lastPageLoaded + 1, _searchBrowseState.value.filter)
        }
    }

    private fun clean() {
        _searchBrowseState.update { SearchState() }
    }

    private fun loadPageWithName(page: Int, name: String) {
        viewModelScope.launch {
            charactersRepository.loadCharactersPreviewsPageByName(page = page, name = name)
                .collect { result ->
                    when (result) {
                        is Result.Success<PagedCharactersResult> -> processResult(result.data)
                        is Result.Error<Error> -> processError(result.error)
                    }
                }
        }
    }

    private fun processResult(pagedCharactersResult: PagedCharactersResult) {
        _searchBrowseState.update {
            val currentPreviews = it.filteredCharacters
            val updatedPreviews = currentPreviews + pagedCharactersResult.charactersPreviews
            it.copy(
                filteredCharacters = updatedPreviews,
                lastPageLoaded = pagedCharactersResult.currentPage,
                totalPages = pagedCharactersResult.totalPages
            )
        }
    }

    private fun processError(error: Error) {

    }
}