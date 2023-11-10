package com.example.rickyandmortyshowcase.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.asLiveData
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.rickyandmortyshowcase.R
import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterDetailed
import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterSimple
import com.example.rickyandmortyshowcase.ui.RaMSViewModel
import com.example.rickyandmortyshowcase.ui.utils.RaMSContentType
import com.example.rickyandmortyshowcase.ui.utils.RaMSNavigationType

@Composable
fun RaMSListOnlyContent(
    state: RaMSViewModel.RickAndMortyShowcaseState,
    onSelectCharacter: (id: String) -> Unit,
    onEnterSearch: () -> Unit,
    onEnterCharacters: () -> Unit,
    onAddCharacterToFavorites: (id: String) -> Unit,
    onRemoveCharacterFromFavorites: (id: String) -> Unit,
    onFilterCharacters: (name: String) -> Unit,
    contentType: RaMSContentType,
    modifier: Modifier = Modifier
) {
    modifier.fillMaxSize()
    if (state.isShowingHomepage) {
        when (state.currentCharactersList) {
            RaMSViewModel.CharactersListType.CHARACTERS -> {
                CharactersScreen(
                    state = state,
                    onSelectCharacter = onSelectCharacter,
                    onEnterSearch = onEnterSearch,
                    modifier = modifier
                )
            }

            RaMSViewModel.CharactersListType.FILTER -> {
                FilterCharacterScreen(
                    state = state,
                    onSelectCharacter = onSelectCharacter,
                    onFilterCharacters = onFilterCharacters,
                    onEnterCharacters = onEnterCharacters,
                    modifier = modifier
                )
            }

            RaMSViewModel.CharactersListType.FAVORITES -> {
                FavoriteCharactersScreen(
                    state = state,
                    onSelectCharacter = onSelectCharacter,
                    modifier = modifier
                )
            }
        }
    } else {
        if (!state.isCharacterDetailsListLoading) {
            CharacterDetailsTopBar(
                state = state,
                onEnterCharacters = onEnterCharacters,
                selectedCharacter = state.selectedCharacter!!,
                onAddCharacterToFavorites = onAddCharacterToFavorites,
                onRemoveCharacterFromFavorites = onRemoveCharacterFromFavorites,
                contentType = contentType
            )
            CharacterDetailsScreen(
                selectedCharacter = state.selectedCharacter,
                modifier = modifier
            )
        } else {
            CircularProgressIndicator(
                modifier = modifier
            )
        }
    }
}

@Composable
fun RaMSListAndDetailContent(
    state: RaMSViewModel.RickAndMortyShowcaseState,
    onSelectCharacter: (id: String) -> Unit,
    onEnterSearch: () -> Unit,
    onEnterCharacters: () -> Unit,
    onAddCharacterToFavorites: (id: String) -> Unit,
    onRemoveCharacterFromFavorites: (id: String) -> Unit,
    onFilterCharacters: (name: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxSize()) {
        when (state.currentCharactersList) {
            RaMSViewModel.CharactersListType.CHARACTERS -> {
                CharactersScreen(
                    state = state,
                    onSelectCharacter = onSelectCharacter,
                    onEnterSearch = onEnterSearch,
                    modifier = Modifier.weight(1f)
                )
            }

            RaMSViewModel.CharactersListType.FILTER -> {
                FilterCharacterScreen(
                    state = state,
                    onSelectCharacter = onSelectCharacter,
                    onFilterCharacters = onFilterCharacters,
                    onEnterCharacters = onEnterCharacters,
                    modifier = Modifier.weight(1f)
                )
            }

            RaMSViewModel.CharactersListType.FAVORITES -> {
                FavoriteCharactersScreen(
                    state = state,
                    onSelectCharacter = onSelectCharacter,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            if (state.selectedCharacter != null) {
                CharacterDetailsTopBar(
                    state = state,
                    onEnterCharacters = onEnterCharacters,
                    selectedCharacter = state.selectedCharacter,
                    onAddCharacterToFavorites = onAddCharacterToFavorites,
                    onRemoveCharacterFromFavorites = onRemoveCharacterFromFavorites,
                    contentType = RaMSContentType.LIST_AND_DETAIL
                )
                CharacterDetailsScreen(
                    selectedCharacter = state.selectedCharacter,
                    modifier = modifier
                )
            } else {
                CharacterDetailsEmptyScreen(
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
fun CharactersListItem(
    state: RaMSViewModel.RickAndMortyShowcaseState,
    character: CharacterSimple,
    selected: Boolean,
    filterMode: Boolean,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isCharacterInFavorites by remember {
        mutableStateOf(value = state.favoriteCharacters.asLiveData().value!!.firstOrNull { it.id == character.id } != null)
    }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.list_item_padding)),
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
                modifier = Modifier
                    .padding(
                        dimensionResource(id = R.dimen.list_item_image_padding)
                    )
                    .size(dimensionResource(id = R.dimen.list_item_image_size))
                    .clip(MaterialTheme.shapes.small)
            )
            Column {
                Row {
                    Text(text = character.name, style = MaterialTheme.typography.headlineMedium)
                    AnimatedVisibility(visible = isCharacterInFavorites) {
                        Image(
                            painter = painterResource(id = R.drawable.favorites_selected),
                            contentDescription = stringResource(id = R.string.favorite),
                            modifier = modifier
                                .size(dimensionResource(id = R.dimen.list_item_favorite_icon_size))
                                .padding(start = dimensionResource(R.dimen.list_item_favorite_icon_padding_start))
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
fun CharactersScreen(
    state: RaMSViewModel.RickAndMortyShowcaseState,
    onSelectCharacter: (id: String) -> Unit,
    onEnterSearch: () -> Unit,
    modifier: Modifier = Modifier

) {
    Box(modifier = modifier.fillMaxSize()) {
        if (!state.isHomepageLoading) {
            val characters = state.characters
            Column {
                CharactersListTopBar(
                    onEnterSearch = onEnterSearch,
                )
                LazyColumn(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.list_item_padding))
                ) {

                    items(characters, key = { character -> character.id }) { character ->
                        CharactersListItem(
                            state = state,
                            character = character,
                            selected = false,
                            filterMode = state.currentCharactersList == RaMSViewModel.CharactersListType.FILTER,
                            onCardClick = { onSelectCharacter(character.id) },
                        )
                    }
                }
            }
        } else {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun FavoriteCharactersScreen(
    state: RaMSViewModel.RickAndMortyShowcaseState,
    onSelectCharacter: (id: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        val characters = state.favoriteCharacters.asLiveData().value!!
        LazyColumn(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.list_item_padding))
        ) {
            item {
                FavoriteCharactersTopBar()
            }
            items(characters, key = { character -> character.id }) { character ->
                CharactersListItem(
                    state = state,
                    character = character,
                    selected = false,
                    filterMode = state.currentCharactersList == RaMSViewModel.CharactersListType.FILTER,
                    onCardClick = { onSelectCharacter(character.id) },
                )
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CharacterDetailsScreen(
    selectedCharacter: CharacterDetailed,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Card {
            Column {
                Row(modifier = Modifier) {
                    Image(
                        painter = rememberImagePainter(selectedCharacter.imageUrl),
                        contentDescription = "",
                        modifier = Modifier.size(dimensionResource(id = R.dimen.character_detail_image_size))
                    )
                    Column {
                        Text(
                            text = stringResource(id = R.string.name),
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                        Text(
                            text = selectedCharacter.name,
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }
                }
                Divider()
                CharacterDetailsTraitElement(
                    labelText = stringResource(id = R.string.status),
                    text = selectedCharacter.status
                )
                CharacterDetailsTraitElement(
                    labelText = stringResource(id = R.string.species),
                    text = selectedCharacter.species
                )
                CharacterDetailsTraitElement(
                    labelText = stringResource(id = R.string.type),
                    text = selectedCharacter.type
                )
                CharacterDetailsTraitElement(
                    labelText = stringResource(id = R.string.gender),
                    text = selectedCharacter.gender
                )
                CharacterDetailsTraitElement(
                    labelText = stringResource(id = R.string.origin),
                    text = selectedCharacter.origin
                )
                CharacterDetailsTraitElement(
                    labelText = stringResource(id = R.string.location),
                    text = selectedCharacter.location
                )
            }
        }
    }
}

@Composable
fun CharacterDetailsEmptyScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(
            text = stringResource(id = R.string.nothing_to_display),
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onTertiary
        )
    }
}

@Composable
fun FilterCharacterScreen(
    state: RaMSViewModel.RickAndMortyShowcaseState,
    onSelectCharacter: (id: String) -> Unit,
    onFilterCharacters: (name: String) -> Unit,
    onEnterCharacters: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        if (!state.isHomepageLoading) {
            val characters = state.characters
            if (state.filter.isNotEmpty()) {
                if (characters.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.list_item_padding))
                    ) {
                        item {
                            FilterCharactersTopBar(
                                onEnterCharacters = onEnterCharacters,
                                onFilterCharacters = onFilterCharacters,
                            )
                        }
                        items(characters, key = { character -> character.id }) { character ->
                            CharactersListItem(
                                state = state,
                                character = character,
                                selected = false,
                                filterMode = state.currentCharactersList == RaMSViewModel.CharactersListType.FILTER,
                                onCardClick = { onSelectCharacter(character.id) },
                            )
                        }
                    }
                } else {
                    Text(
                        text = stringResource(id = R.string.nothing_to_display),
                        style = MaterialTheme.typography.displayLarge,
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                }
            } else {
                Text(
                    text = stringResource(id = R.string.type_something_to_search),
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
        } else {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun CharacterDetailsTraitElement(
    labelText: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(vertical = dimensionResource(id = R.dimen.details_trait_element_padding_vertical))) {
        Text(
            text = labelText,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onSecondary
        )
        Text(
            text = text,
            style = MaterialTheme.typography.displayMedium
        )
    }
}

//@Preview
//@Composable
//fun RaMSListAndDetailContentPreview() {
//    RickyAndMortyShowcaseTheme {
//        RaMSListAndDetailContent()
//    }
//}
//
//@Preview
//@Composable
//fun RaMSListOnlyContentPreview() {
//    RickyAndMortyShowcaseTheme {
//        ReplyListOnlyContent()
//    }
//}
//@Preview
//@Composable
//fun FilterCharacterScreenPreview() {
//    RickyAndMortyShowcaseTheme {
//        FilterCharacterScreen()
//    }
//}
//
//@Preview
//@Composable
//fun CharacterDetailsScreenPreview() {
//    RickyAndMortyShowcaseTheme {
//        CharacterDetailsScreen()
//    }
//}
//
//@Preview
//@Composable
//fun CharacterScreenPreview() {
//    RickyAndMortyShowcaseTheme {
//        CharacterScreen()
//    }
//}