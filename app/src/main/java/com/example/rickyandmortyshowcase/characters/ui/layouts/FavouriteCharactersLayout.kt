package com.example.rickyandmortyshowcase.characters.ui.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.rickyandmortyshowcase.R
import com.example.rickyandmortyshowcase.characters.ui.viewmodel.CharactersState
import com.example.rickyandmortyshowcase.core.ui.BlurryBottomShadeRow

@Composable
fun FavoriteCharactersLayout(
    state: CharactersState,
    onSelectCharacter: (id: String) -> Unit,
    onEnterCharacters: () -> Unit,
    modifier: Modifier = Modifier
) {
//    BackHandler {
//        onEnterCharacters()
//    }
//    Box(
//        modifier = modifier
//            .fillMaxSize()
//    ) {
//        val characters = state.favoriteCharacters.asLiveData().value!!
//        Column {
//            FavoriteCharactersTopBar()
//            if (!state.isHomepageLoading){
//                LazyColumn(
//                    modifier = Modifier.padding(dimensionResource(id = R.dimen.body_padding)),
//                    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.list_item_padding))
//                ) {
//                    items(characters, key = { character -> character.id }) { character ->
//                        CharactersListItem(
//                            state = state,
//                            character = character,
//                            selected = false,
//                            filterMode = state.currentCharactersList == RaMSViewModel.CharactersListType.FILTER,
//                            onCardClick = { onSelectCharacter(character.id) },
//                        )
//                    }
//                }
//            } else {
//                CircularProgressIndicator(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .wrapContentSize(Alignment.Center)
//                )
//            }
//        }
//    }
}

@Composable
fun FavoriteCharactersTopBar(
    modifier: Modifier = Modifier
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
                    text = stringResource(id = R.string.favorites),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.top_bar_padding_horizontal))
                )

            }
        }

    }
}