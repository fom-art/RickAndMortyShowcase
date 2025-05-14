package com.fomart.rms.shared.feature.favorites.presentation

import CharactersPreviewsList
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.fomart.rms.core.designsystem.components.DefaultTopBar
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import rickandmortyshowcase.shared.feature.favorites.generated.resources.Res
import rickandmortyshowcase.shared.feature.favorites.generated.resources.favorites

@Composable
fun FavoritesRoute(
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = koinViewModel(),
    navigateToCharacterDetails: (id: String) -> Unit
) {
    val favoriteCharactersPreviews = viewModel.favoriteCharactersPreviews.collectAsState().value

    Scaffold(
        modifier = modifier,
        topBar = {
            DefaultTopBar(
                title = stringResource(Res.string.favorites),
            )
        }
    ) { innerPadding ->
        CharactersPreviewsList(
            modifier = modifier.padding(innerPadding),
            charactersPreviews = favoriteCharactersPreviews,
            searchMode = false,
            onSelectCharacter = { characterPreview -> navigateToCharacterDetails(characterPreview.id) },
        )
    }
}