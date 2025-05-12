package com.fomart.rms.shared.feature.catalog.presentation

import CharactersPreviewsList
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CatalogRoute(
    modifier: Modifier = Modifier,
    viewModel: CatalogViewModel = koinViewModel(),
    navigateToCharacterDetails: (id: String) -> Unit
) {
    val state = viewModel.catalogState.collectAsState().value

    CharactersPreviewsList(
        modifier = modifier,
        charactersPreviews = state.charactersPreviews,
        searchMode = false,
        onSelectCharacter = { characterPreview -> navigateToCharacterDetails(characterPreview.id) },
        canLoadMode = !state.isLastPageLoaded,
        loadMore = viewModel::loadNextPage
    )
}