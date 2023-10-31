package com.example.rickyandmortyshowcase.ui.screens

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.rickyandmortyshowcase.R
import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterSimple
import com.example.rickyandmortyshowcase.ui.RaMSViewModel

@Composable
fun RaMSListOnlyContent(
    state: RaMSViewModel.RickAndMortyShowcaseState,
    onSelectCharacter: (id: String) -> Unit,
    onEnterSearch: () -> Unit,
    onEnterCharacters: () -> Unit,
    onAddCharacterToFavorites: (id: String) -> Unit,
    onRemoveCharacterFromFavorites: (id: String) -> Unit,
    onFilterCharacters: (name: String) -> Unit,
    modifier: Modifier = Modifier
) {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersListItem(
    character: CharacterSimple,
    selected: Boolean,
    filterMode: Boolean,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
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
                painter = BitmapPainter(character.image.asImageBitmap()),
                contentDescription = character.name,
                modifier = Modifier
                    .padding(
                        dimensionResource(id = R.dimen.list_item_image_padding)
                    )
                    .size(dimensionResource(id = R.dimen.list_item_image_size))
            )
            Column {
                Row {
                    Text(text = character.name, style = MaterialTheme.typography.headlineMedium)
                    Image(
                        painter = painterResource(id = R.drawable.favorites_selected),
                        contentDescription = stringResource(id = R.string.favorite),
                        modifier = modifier
                            .size(dimensionResource(id = R.dimen.list_item_favorite_icon_size))
                            .padding(start = dimensionResource(R.dimen.list_item_favorite_icon_padding_start))
                    )
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
    Row(modifier = modifier) {

    }
}

@Composable
fun CharacterScreen(
    state: RaMSViewModel.RickAndMortyShowcaseState,
    onSelectCharacter: (id: String) -> Unit,
    onEnterSearch: () -> Unit,
    modifier: Modifier = Modifier

) {
    Box(modifier = modifier.fillMaxSize()) {
        if (!state.isCharactersListLoading) {
            val characters = state.characters
            LazyColumn(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.list_item_padding))
            ) {
                item {
                    CharactersListTopBar(
                        onEnterSearch = onEnterSearch,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimensionResource(id = R.dimen.top_bar_padding_vertical))
                    )
                }
                items(characters, key = { character -> character.id }) { character ->
                    CharactersListItem(
                        character = character,
                        selected = false,
                        filterMode = state.currentCharactersList == RaMSViewModel.CharactersListType.FILTER,
                        onCardClick = { onSelectCharacter(character.id) }
                    )
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
fun CharacterDetailsScreen() {

}

@Composable
fun FilterCharacterScreen(
    state: RaMSViewModel.RickAndMortyShowcaseState,
    onSelectCharacter: (id: String) -> Unit,
    onEnterCharacters: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        if (!state.isCharactersListLoading) {
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
                                onSelectCharacter = onSelectCharacter,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = dimensionResource(id = R.dimen.top_bar_padding_vertical))
                            )
                        }
                        items(characters, key = { character -> character.id }) { character ->
                            CharactersListItem(
                                character = character,
                                selected = false,
                                filterMode = state.currentCharactersList == RaMSViewModel.CharactersListType.FILTER,
                                onCardClick = { onSelectCharacter(character.id) }
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