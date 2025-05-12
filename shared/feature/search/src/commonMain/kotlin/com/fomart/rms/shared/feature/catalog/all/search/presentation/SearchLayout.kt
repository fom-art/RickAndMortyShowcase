package com.fomart.rms.shared.feature.catalog.all.search.presentation

import CharactersPreviewsList
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.fomart.rms.core.designsystem.components.BlurryBottomShadeBox
import com.fomart.rms.core.model.CharacterPreview
import org.jetbrains.compose.resources.stringResource
import rickandmortyshowcase.shared.feature.search.generated.resources.Res
import rickandmortyshowcase.shared.feature.search.generated.resources.search_characters

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
    var query by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BlurryBottomShadeBox {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = query,
                onValueChange = { newValue ->
                    query = newValue
                    onSearch(newValue)
                },
                placeholder = {
                    Text(
                        stringResource(Res.string.search_characters),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                leadingIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            tint = MaterialTheme.colorScheme.onBackground,
                            contentDescription = "Go Back"
                        )
                    }
                },
                trailingIcon = {
                    IconButton(onClick = clear) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            tint = MaterialTheme.colorScheme.onBackground,
                            contentDescription = "Clean"
                        )
                    }
                }
            )
        }

        CharactersPreviewsList(
            charactersPreviews = displayedCharacters,
            searchMode = true,
            onSelectCharacter = onSelectCharacter,
            canLoadMode = canLoadMode,
            loadMore = loadMore
        )
    }
}
