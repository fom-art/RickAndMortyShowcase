package com.example.rickyandmortyshowcase.characters.ui.layouts

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.rickyandmortyshowcase.R
import com.example.rickyandmortyshowcase.characters.ui.CharactersScreenContentDisplayType
import com.example.rickyandmortyshowcase.characters.ui.viewmodel.CharactersState

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CharacterDetailsLayout(
    state: CharactersState,
    onEnterCharacters: () -> Unit,
    onAddCharacterToFavorites: (id: String) -> Unit,
    onRemoveCharacterFromFavorites: (id: String) -> Unit,
    charactersScreenContentDisplayType: CharactersScreenContentDisplayType,
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
            charactersScreenContentDisplayType = charactersScreenContentDisplayType
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

@Composable
fun CharacterDetailsEmptyLayout(
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
fun CharacterDetailsTopBar(
    state: CharactersState,
    onEnterCharacters: () -> Unit,
    onAddCharacterToFavorites: (id: String) -> Unit,
    onRemoveCharacterFromFavorites: (id: String) -> Unit,
    charactersScreenContentDisplayType: CharactersScreenContentDisplayType,
    modifier: Modifier = Modifier
) {
//    var isSelectedCharacterInFavorites by remember {
//        mutableStateOf(
//            value = state.favoriteCharacters().any { it.id == state.selectedCharacter?.id }
//        )
//    }
//    Box(
//        modifier = modifier
//            .background(MaterialTheme.colorScheme.secondaryContainer)
//    ) {
//        BlurryBottomShadeRow {
//            Row(
//                modifier = Modifier
//                    .height(dimensionResource(id = R.dimen.top_bar_height))
//                    .padding(dimensionResource(id = R.dimen.top_bar_padding_vertical)),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                AnimatedVisibility(visible = contentType == RaMSContentType.LIST_ONLY) {
//                    IconButton(
//                        onClick = onEnterCharacters,
//                        modifier = Modifier
//                            .padding(horizontal = dimensionResource(R.dimen.detail_topbar_back_button_padding_horizontal))
//                    )
//                    {
//                        Image(
//                            painter = painterResource(id = R.drawable.back_arrow),
//                            contentDescription = stringResource(id = R.string.back_to_characters)
//                        )
//                    }
//                }
//                Text(
//                    text = state.selectedCharacter!!.name,
//                    style = MaterialTheme.typography.headlineLarge,
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(start = dimensionResource(id = R.dimen.top_bar_padding_horizontal))
//                )
//                IconButton(
//                    onClick = {
//                        isSelectedCharacterInFavorites = if (isSelectedCharacterInFavorites) {
//                            onRemoveCharacterFromFavorites(state.selectedCharacter!!.id)
//                            !isSelectedCharacterInFavorites
//                        } else {
//                            onAddCharacterToFavorites(state.selectedCharacter!!.id)
//                            !isSelectedCharacterInFavorites
//                        }
//                    },
//                    modifier = Modifier
//                        .padding(horizontal = dimensionResource(R.dimen.detail_topbar_back_button_padding_horizontal))
//                        .background(MaterialTheme.colorScheme.surface, shape = CircleShape)
//                ) {
//                    Crossfade(
//                        targetState = isSelectedCharacterInFavorites,
//                        label = ""
//                    ) { targetState ->
//                        Image(
//                            painter = if (targetState) painterResource(id = R.drawable.favorites_selected)
//                            else painterResource(id = R.drawable.favorites_unselected),
//                            contentDescription = if (targetState) stringResource(id = R.string.remove_from_favorites)
//                            else stringResource(id = R.string.add_to_favorites)
//                        )
//                    }
//                }
//            }
//        }
//    }
}