package com.fomart.rms.feature.characters.catalog.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.rickyandmortyshowcase.R
import com.fomart.rms.characters.ui.viewmodel.CharactersListType
import com.fomart.rms.characters.ui.viewmodel.CharactersState
import com.fomart.rms.core.designsystem.BlurryBottomShadeRow
import com.fomart.rms.core.model.CharacterPreview
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CharactersCatalogScreen(
    state: CharactersState,
    onSelectCharacter: (id: String) -> Unit,
    onEnterSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column {
            CharactersListTopBar(
                onEnterSearch = onEnterSearch,
            )
            if (!state.isHomepageLoading) {
                val characters = state.characters

                LazyColumn(
                    modifier = padding(dimensionResource(id = R.dimen.body_padding)),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.list_item_padding))
                ) {

                    items(characters, key = { character -> character.id }) { character ->
                        CharactersCatalogItem(
                            state = state,
                            character = character,
                            selected = false,
                            filterMode = state.currentCharactersList == CharactersListType.FILTER,
                            onCardClick = { onSelectCharacter(character.id) },
                        )
                    }
                }
            } else {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun CharactersCatalogItem(
    state: CharactersState,
    character: CharacterPreview,
    selected: Boolean,
    filterMode: Boolean,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isCharacterInFavorites =
        state.favoriteCharacters.firstOrNull { it.id == character.id } != null
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selected && filterMode)
                MaterialTheme.colorScheme.background
            else if (selected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.tertiaryContainer
        ),
        onClick = onCardClick
    ) {
        Row {
            Image(
                painter = rememberImagePainter(character.imageUrl),
                contentDescription = character.name,
                modifier = padding(
                    8.dp
                )
                    .size(16.dp)
                    .clip(MaterialTheme.shapes.small)
            )
            Column {
                Row {
                    Text(
                        text = character.name,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    AnimatedVisibility(visible = isCharacterInFavorites) {
                        Image(
                            imageVector = Icons.Filled.Search,
                            contentDescription = stringResource(id = R.string.favorite),
                            modifier = modifier
                                .size(8.dp)
                                .padding(start = 16.dp)
                        )
                    }
                }
                Text(
                    text = character.status,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}

@Composable
fun CharactersListTopBar(
    onEnterSearch: () -> Unit, modifier: Modifier = Modifier
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
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Text(
                    text = stringResource(id = R.string.characters),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
                IconButton(
                    onClick = onEnterSearch, modifier = Modifier
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.search_characters)
                    )
                }
            }
        }
    }
}
