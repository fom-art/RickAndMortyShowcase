package com.fomart.rms.shared.feature.catalog.all.search.presentation

import CharactersPreviewsList
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fomart.rms.core.model.CharacterPreview

@Composable
fun SearchLayout(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
    clear: () -> Unit,
    canLoadMode: Boolean,
    displayedCharacters: List<CharacterPreview>,
    loadMore: () -> Unit,
    onSelectCharacter: (CharacterPreview) -> Unit,
    navigateBack: () -> Unit
) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            SearchTopAppBar(
                onSearch = onSearch,
                clear = clear,
                navigateBack = navigateBack
            )
        }
    ) { innerPadding ->
        CharactersPreviewsList(
            modifier = Modifier.padding(innerPadding),
            charactersPreviews = displayedCharacters,
            searchMode = true,
            onSelectCharacter = onSelectCharacter,
            canLoadMode = canLoadMode,
            loadMore = loadMore
        )
    }
}