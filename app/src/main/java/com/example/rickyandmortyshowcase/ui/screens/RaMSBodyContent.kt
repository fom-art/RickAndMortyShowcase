package com.example.rickyandmortyshowcase.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.rickyandmortyshowcase.R
import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterDetailed
import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterSimple
import com.example.rickyandmortyshowcase.ui.RaMSViewModel
import com.example.rickyandmortyshowcase.ui.RickAndMortyShowcaseState
import com.example.rickyandmortyshowcase.ui.utils.RaMSContentType

@Composable
fun RaMSListOnlyContent(
    state: RickAndMortyShowcaseState,
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
                    onEnterCharacters = onEnterCharacters,
                    modifier = modifier
                )
            }
        }
    } else {
        if (!state.isCharacterDetailsListLoading) {
            CharacterDetailsScreen(
                state = state,
                onEnterCharacters = onEnterCharacters,
                onAddCharacterToFavorites = onAddCharacterToFavorites,
                onRemoveCharacterFromFavorites = onRemoveCharacterFromFavorites,
                contentType = RaMSContentType.LIST_ONLY,
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
    state: RickAndMortyShowcaseState,
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
                    onEnterCharacters = onEnterSearch,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            if (state.selectedCharacter != null) {
                CharacterDetailsScreen(
                    state = state,
                    onEnterCharacters = onEnterCharacters,
                    onAddCharacterToFavorites = onAddCharacterToFavorites,
                    onRemoveCharacterFromFavorites = onRemoveCharacterFromFavorites,
                    contentType = RaMSContentType.LIST_AND_DETAIL,
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
    state: RickAndMortyShowcaseState,
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
                    Text(
                        text = character.name,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
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
    state: RickAndMortyShowcaseState,
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
                            filterMode = state.currentCharactersList == RaMSViewModel.CharactersListType.FILTER,
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
fun FavoriteCharactersScreen(
    state: RickAndMortyShowcaseState,
    onSelectCharacter: (id: String) -> Unit,
    onEnterCharacters: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onEnterCharacters()
    }
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        val characters = state.favoriteCharacters.asLiveData().value!!
        Column {
            FavoriteCharactersTopBar()
            if (!state.isHomepageLoading){
                LazyColumn(
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.body_padding)),
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

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CharacterDetailsScreen(
    state: RickAndMortyShowcaseState,
    onEnterCharacters: () -> Unit,
    onAddCharacterToFavorites: (id: String) -> Unit,
    onRemoveCharacterFromFavorites: (id: String) -> Unit,
    contentType: RaMSContentType,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onEnterCharacters()
    }
    Column {
        CharacterDetailsTopBar(
            state = state,
            onEnterCharacters = onEnterCharacters,
            onAddCharacterToFavorites = onAddCharacterToFavorites,
            onRemoveCharacterFromFavorites = onRemoveCharacterFromFavorites,
            contentType = contentType
        )
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.body_padding))
        ) {
            item {
                Card(modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.character_details_card_padding))) {
                    Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.character_details_card_boddy_padding))) {
                        Row(modifier = Modifier) {
                            Image(
                                painter = rememberImagePainter(state.selectedCharacter!!.imageUrl),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(dimensionResource(id = R.dimen.character_detail_image_size))
                                    .clip(MaterialTheme.shapes.medium)
                            )
                            Column(modifier = Modifier.padding(start = dimensionResource(id = R.dimen.character_detaild_name_section_padding_start))) {
                                Text(
                                    text = stringResource(id = R.string.name),
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = MaterialTheme.colorScheme.onSecondary
                                )
                                Text(
                                    text = state.selectedCharacter.name,
                                    style = MaterialTheme.typography.headlineLarge,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                        Divider(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.character_details_divider_vertical_padding)))
                        CharacterDetailsTraitElement(
                            labelText = stringResource(id = R.string.status),
                            text = state.selectedCharacter!!.status
                        )
                        CharacterDetailsTraitElement(
                            labelText = stringResource(id = R.string.species),
                            text = state.selectedCharacter.species
                        )
                        CharacterDetailsTraitElement(
                            labelText = stringResource(id = R.string.type),
                            text = state.selectedCharacter.type
                        )
                        CharacterDetailsTraitElement(
                            labelText = stringResource(id = R.string.gender),
                            text = state.selectedCharacter.gender
                        )
                        CharacterDetailsTraitElement(
                            labelText = stringResource(id = R.string.origin),
                            text = state.selectedCharacter.origin
                        )
                        CharacterDetailsTraitElement(
                            labelText = stringResource(id = R.string.location),
                            text = state.selectedCharacter.location
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterDetailsEmptyScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.body_padding))
    ) {
        Text(
            text = stringResource(id = R.string.nothing_to_display),
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onTertiary,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun FilterCharacterScreen(
    state: RickAndMortyShowcaseState,
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
                                    filterMode = state.currentCharactersList == RaMSViewModel.CharactersListType.FILTER,
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
fun CharacterDetailsTraitElement(
    labelText: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(vertical = dimensionResource(id = R.dimen.details_trait_element_padding_vertical))) {
        Text(
            text = labelText,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondary
        )
        Text(
            text = text,
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onPrimary
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