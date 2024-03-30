package com.example.rickyandmortyshowcase.characters.ui.layouts

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.rickyandmortyshowcase.R
import com.example.rickyandmortyshowcase.characters.ui.viewmodel.CharactersListType
import com.example.rickyandmortyshowcase.characters.ui.viewmodel.CharactersState
import com.example.rickyandmortyshowcase.characters.ui.viewmodel.CharactersViewModel
import com.example.rickyandmortyshowcase.core.ui.BlurryBottomShadeRow

@Composable
fun FilterCharacterLayout(
    state: CharactersState,
    onSelectCharacter: (id: String) -> Unit,
    onFilterCharacters: (name: String) -> Unit,
    onEnterCharacters: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onEnterCharacters()
    }
    val characters = state.filteredCharacters
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        FilterCharactersTopBar(
            state = state,
            onEnterCharacters = onEnterCharacters,
            onFilterCharacters = onFilterCharacters,
        )
        Box(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.body_padding))
        ) {
            if (!state.isHomepageLoading) {
                if (state.filter.isNotEmpty()) {
                    if (characters.isNotEmpty()) {
                        LazyColumn(
                            modifier = Modifier,
                            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.list_item_padding))
                        ) {
                            items(characters, key = { character -> character.id }) { character ->
                                CharactersListItem(
                                    state = state,
                                    character = character,
                                    selected = false,
                                    filterMode = state.currentCharactersList == CharactersListType.FILTER,
                                    onCardClick = { onSelectCharacter(character.id) },
                                )
                            }
                        }
                    } else {
                        Text(
                            text = stringResource(id = R.string.nothing_to_display),
                            style = MaterialTheme.typography.displayLarge,
                            color = MaterialTheme.colorScheme.onTertiary,
                            modifier = modifier
                                .fillMaxSize()
                                .wrapContentSize(Alignment.Center),
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    Text(
                        text = stringResource(id = R.string.type_something_to_search),
                        style = MaterialTheme.typography.displayLarge,
                        color = MaterialTheme.colorScheme.onTertiary,
                        modifier = modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center),
                        textAlign = TextAlign.Center,
                    )
                }
            } else {
                CircularProgressIndicator(
                    modifier = modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun FilterCharactersTopBar(
    state: CharactersState,
    onEnterCharacters: () -> Unit,
    onFilterCharacters: (name: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        BlurryBottomShadeRow {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .height(dimensionResource(id = R.dimen.top_bar_height))
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.top_bar_padding_vertical))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
            ) {
                IconButton(onClick = onEnterCharacters) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.enter_characters_list)
                    )
                }
                TextField(
                    value = state.filter,
                    placeholder = { Text(
                        text = stringResource(id = R.string.search_characters),
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.weight(1f)
                    )},
                    singleLine = true,
                    onValueChange = onFilterCharacters,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = dimensionResource(R.dimen.filter_text_input_padding_start))
                )
            }
        }
    }
}