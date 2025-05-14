package com.fomart.rms.shared.feature.catalog.presentation

import CharactersPreviewsList
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.fomart.rms.core.designsystem.components.DefaultTopBar
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import rickandmortyshowcase.shared.feature.catalog.generated.resources.Res
import rickandmortyshowcase.shared.feature.catalog.generated.resources.characters
import rickandmortyshowcase.shared.feature.catalog.generated.resources.search_characters

@Composable
fun CatalogRoute(
    modifier: Modifier = Modifier,
    viewModel: CatalogViewModel = koinViewModel(),
    navigateToCharacterDetails: (id: String) -> Unit,
    navigateToSearch: () -> Unit,
) {
    val state = viewModel.catalogState.collectAsState().value
    Scaffold(
        modifier = modifier,
        topBar = {
            DefaultTopBar(
                title = stringResource(Res.string.characters),
                trailingIcon = {
                    IconButton(onClick = navigateToSearch) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(
                                Res.string.search_characters
                            ),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        CharactersPreviewsList(
            modifier = Modifier.padding(innerPadding),
            charactersPreviews = state.charactersPreviews,
            searchMode = false,
            onSelectCharacter = { characterPreview -> navigateToCharacterDetails(characterPreview.id) },
            canLoadMode = !state.isLastPageLoaded,
            loadMore = viewModel::loadNextPage
        )
    }
}