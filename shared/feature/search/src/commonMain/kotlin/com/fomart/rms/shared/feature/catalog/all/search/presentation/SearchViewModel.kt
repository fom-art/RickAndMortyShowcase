package com.fomart.rms.shared.feature.catalog.all.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fomart.rms.core.data.domain.repository.CharactersRepository
import com.fomart.rms.core.model.domain.Result
import com.fomart.rms.core.model.domain.error.Error
import com.fomart.rms.core.model.PagedCharactersResult
import com.fomart.rms.shared.feature.catalog.all.search.domain.SearchState
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
            loadPageWithName(1, name, cleanList = true)
        }
    }

    fun loadNextPage() {
        val state = _searchBrowseState.value
        if (!state.isLastPageLoaded) {
            loadPageWithName(state.lastPageLoaded + 1, _searchBrowseState.value.filter)
        }
    }

    fun setExpanded(expanded: Boolean) {
        _searchBrowseState.update {
            it.copy(
                expanded = expanded
            )
        }
    }

    fun clean() {
        _searchBrowseState.update { SearchState() }
    }

    private fun loadPageWithName(page: Int, name: String, cleanList: Boolean = false) {
        viewModelScope.launch {
            charactersRepository.loadCharactersPreviewsPageByName(page = page, name = name)
                .collect { result ->
                    when (result) {
                        is Result.Success<PagedCharactersResult> -> processResult(
                            result.data,
                            cleanList
                        )

                        is Result.Error<Error> -> processError(result.error)
                    }
                }
        }
    }

    private fun processResult(
        pagedCharactersResult: PagedCharactersResult,
        cleanList: Boolean = false
    ) {
        _searchBrowseState.update {
            val currentPreviews = if (cleanList) emptyList() else it.filteredCharacters
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