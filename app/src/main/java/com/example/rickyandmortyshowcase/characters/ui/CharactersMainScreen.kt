package com.example.rickyandmortyshowcase.characters.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.rickyandmortyshowcase.R
import com.example.rickyandmortyshowcase.characters.ui.viewmodel.CharactersListType
import com.example.rickyandmortyshowcase.characters.ui.viewmodel.CharactersState
import com.example.rickyandmortyshowcase.characters.ui.viewmodel.CharactersViewModel
import com.example.rickyandmortyshowcase.core.ui.NavigationItemContent

@Composable
fun CharactersMainScreen(
    state: CharactersState,
    windowSize: WindowWidthSizeClass,
    onSelectCharacter: (id: String) -> Unit,
    onEnterSearch: () -> Unit,
    onEnterCharacters: () -> Unit,
    onEnterFavorites: () -> Unit,
    onAddCharacterToFavorites: (id: String) -> Unit,
    onRemoveCharacterFromFavorites: (id: String) -> Unit,
    onFilterCharacters: (name: String) -> Unit
) {
    val navigationItemContentList = listOf(
        NavigationItemContent(
            charactersListType = CharactersListType.CHARACTERS,
            icon = ImageVector.vectorResource(id = state.charactersIconResource),
            text = stringResource(id = R.string.characters)
        ), NavigationItemContent(
            charactersListType = CharactersListType.FAVORITES,
            icon = ImageVector.vectorResource(id = state.favoritesIconResource),
            text = stringResource(id = R.string.favorite)
        )
    )

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            CompactScreen(
                state = state,
                onSelectCharacter = onSelectCharacter,
                onEnterSearch = onEnterSearch,
                onEnterCharacters = onEnterCharacters,
                onEnterFavorites = onEnterFavorites,
                onAddCharacterToFavorites = onAddCharacterToFavorites,
                onRemoveCharacterFromFavorites = onRemoveCharacterFromFavorites,
                onFilterCharacters = onFilterCharacters,
                navigationItemContentList = navigationItemContentList
            )
        }
        WindowWidthSizeClass.Medium -> {
            MediumScreen(
                state = state,
                onSelectCharacter = onSelectCharacter,
                onEnterSearch = onEnterSearch,
                onEnterCharacters = onEnterCharacters,
                onEnterFavorites = onEnterFavorites,
                onAddCharacterToFavorites = onAddCharacterToFavorites,
                onRemoveCharacterFromFavorites = onRemoveCharacterFromFavorites,
                onFilterCharacters = onFilterCharacters,
                navigationItemContentList = navigationItemContentList
            )
        }
        WindowWidthSizeClass.Expanded -> {
            ExpandedScreen(
                state = state,
                onSelectCharacter = onSelectCharacter,
                onEnterSearch = onEnterSearch,
                onEnterCharacters = onEnterCharacters,
                onEnterFavorites = onEnterFavorites,
                onAddCharacterToFavorites = onAddCharacterToFavorites,
                onRemoveCharacterFromFavorites = onRemoveCharacterFromFavorites,
                onFilterCharacters = onFilterCharacters,
                navigationItemContentList = navigationItemContentList
            )
        }
        else -> {
            CompactScreen(
                state = state,
                onSelectCharacter = onSelectCharacter,
                onEnterSearch = onEnterSearch,
                onEnterCharacters = onEnterCharacters,
                onEnterFavorites = onEnterFavorites,
                onAddCharacterToFavorites = onAddCharacterToFavorites,
                onRemoveCharacterFromFavorites = onRemoveCharacterFromFavorites,
                onFilterCharacters = onFilterCharacters,
                navigationItemContentList = navigationItemContentList
            )
        }
    }
}

