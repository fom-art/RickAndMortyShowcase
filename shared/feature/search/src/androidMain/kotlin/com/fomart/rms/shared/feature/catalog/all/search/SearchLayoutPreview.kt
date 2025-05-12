package com.fomart.rms.shared.feature.catalog.all.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fomart.rms.core.designsystem.theme.RickyAndMortyShowcaseTheme
import com.fomart.rms.core.utils.preview.FakeCharactersRepository
import com.fomart.rms.core.utils.preview.createViewModelFactory
import com.fomart.rms.shared.feature.catalog.all.search.presentation.SearchLayout
import com.fomart.rms.shared.feature.catalog.all.search.presentation.SearchViewModel
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.collectLatest

@Preview(showBackground = true)
@Composable
fun SearchLayoutPreview() {
    RickyAndMortyShowcaseTheme {
        val repository = FakeCharactersRepository()
        val viewModelOwner = checkNotNull(LocalViewModelStoreOwner.current)

        CompositionLocalProvider(LocalViewModelStoreOwner provides viewModelOwner) {
            val searchViewModel: SearchViewModel = viewModel(factory = createViewModelFactory {
                SearchViewModel(repository)
            })

            val searchState = searchViewModel.searchBrowseState.collectAsState().value
            val searchFieldState = rememberTextFieldState()

            LaunchedEffect(searchFieldState) {
                snapshotFlow { searchFieldState.text }
                    .collectLatest { text ->
                        searchViewModel.updateNameFilter(text.toString())
                    }
            }

            SearchLayout(
                modifier = Modifier,
                clear = searchViewModel::clean,
                canLoadMode = !searchState.isLastPageLoaded,
                displayedCharacters = searchState.filteredCharacters,
                loadMore = searchViewModel::loadNextPage,
                onSearch = searchViewModel::updateNameFilter,
                onSelectCharacter = { },
                navigateBack = { }
            )
        }
    }
}
