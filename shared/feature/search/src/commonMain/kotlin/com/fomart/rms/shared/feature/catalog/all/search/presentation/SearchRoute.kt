package com.fomart.rms.shared.feature.catalog.all.search.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchRoute(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = koinViewModel(),
    navigateToCharacterDetails: (id: String) -> Unit,
    navigateBack: () -> Unit,
) {
    val state = viewModel.searchBrowseState.collectAsState().value

    SearchLayout(
        modifier = modifier,
        onSearch = viewModel::updateNameFilter,
        clear = viewModel::clean,
        canLoadMode = !state.isLastPageLoaded,
        displayedCharacters = state.filteredCharacters,
        loadMore = viewModel::loadNextPage,
        onSelectCharacter = { characterPreview -> navigateToCharacterDetails(characterPreview.id) },
        navigateBack = navigateBack
    )
}