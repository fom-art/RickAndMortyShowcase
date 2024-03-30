package com.example.rickyandmortyshowcase.characters.ui.layouts

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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import coil.annotation.ExperimentalCoilApi
import com.example.rickyandmortyshowcase.R
import com.example.rickyandmortyshowcase.characters.domain.CharacterSimple
import com.example.rickyandmortyshowcase.characters.ui.viewmodel.CharactersListType
import com.example.rickyandmortyshowcase.characters.ui.viewmodel.CharactersState
import com.example.rickyandmortyshowcase.characters.ui.viewmodel.CharactersViewModel
import com.example.rickyandmortyshowcase.core.ui.BlurryBottomShadeRow

@Composable
fun CharactersListLayout(
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
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.body_padding)),
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
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
fun CharactersListItem(
    state: CharactersState,
    character: CharacterSimple,
    selected: Boolean,
    filterMode: Boolean,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
//    val isCharacterInFavorites by remember {
//        mutableStateOf(value = state.favoriteCharacters.asLiveData().value!!.firstOrNull { it.id == character.id } != null)
//    }
//    Card(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(dimensionResource(id = R.dimen.list_item_padding)),
//        colors = CardDefaults.cardColors(
//            containerColor = if (selected && filterMode)
//                MaterialTheme.colorScheme.background
//            else if (selected)
//                MaterialTheme.colorScheme.primaryContainer
//            else
//                MaterialTheme.colorScheme.tertiaryContainer
//        ),
//        onClick = onCardClick
//    ) {
//        Row {
//            Image(
//                painter = rememberImagePainter(character.imageUrl),
//                contentDescription = character.name,
//                modifier = Modifier
//                    .padding(
//                        dimensionResource(id = R.dimen.list_item_image_padding)
//                    )
//                    .size(dimensionResource(id = R.dimen.list_item_image_size))
//                    .clip(MaterialTheme.shapes.small)
//            )
//            Column {
//                Row {
//                    Text(
//                        text = character.name,
//                        style = MaterialTheme.typography.headlineMedium,
//                        color = MaterialTheme.colorScheme.onPrimary
//                    )
//                    AnimatedVisibility(visible = isCharacterInFavorites) {
//                        Image(
//                            painter = painterResource(id = R.drawable.favorites_selected),
//                            contentDescription = stringResource(id = R.string.favorite),
//                            modifier = modifier
//                                .size(dimensionResource(id = R.dimen.list_item_favorite_icon_size))
//                                .padding(start = dimensionResource(R.dimen.list_item_favorite_icon_padding_start))
//                        )
//                    }
//                }
//                Text(
//                    text = character.status,
//                    style = MaterialTheme.typography.bodySmall,
//                    color = MaterialTheme.colorScheme.onSecondary
//                )
//            }
//        }
//    }
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
                    .height(dimensionResource(id = R.dimen.top_bar_height))
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.top_bar_padding_vertical))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Text(
                    text = stringResource(id = R.string.characters),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.top_bar_padding_horizontal))
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
