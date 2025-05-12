package com.fomart.rms.shared.feature.favorites.presentation

import CharactersPreviewsList
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.fomart.rms.shared.feature.catalog.all.browse.favorites.FavoritesViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FavoritesRoute(
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = koinViewModel(),
    navigateToCharacterDetails: (id: String) -> Unit
) {
    val favoriteCharactersPreviews = viewModel.favoriteCharactersPreviews.collectAsState().value

    CharactersPreviewsList(
        modifier = modifier,
        charactersPreviews = favoriteCharactersPreviews,
        searchMode = false,
        onSelectCharacter = { characterPreview -> navigateToCharacterDetails(characterPreview.id) },
    )
}