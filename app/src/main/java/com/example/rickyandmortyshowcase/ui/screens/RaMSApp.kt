package com.example.rickyandmortyshowcase.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.rickyandmortyshowcase.R
import com.example.rickyandmortyshowcase.ui.RaMSViewModel
import com.example.rickyandmortyshowcase.ui.utils.NavigationItemContent
import com.example.rickyandmortyshowcase.ui.utils.RaMSContentType
import com.example.rickyandmortyshowcase.ui.utils.RaMSNavigationType

@Composable
fun RaMSApp(
    state: RaMSViewModel.RickAndMortyShowcaseState,
    windowSize: WindowWidthSizeClass,
    onSelectCharacter: (id: String) -> Unit,
    onEnterSearch: () -> Unit,
    onEnterCharacters: () -> Unit,
    onEnterFavorites: () -> Unit,
    onAddCharacterToFavorites: (id: String) -> Unit,
    onRemoveCharacterFromFavorites: (id: String) -> Unit,
    onFilterCharacters: (name: String) -> Unit
) {
    val navigationType: RaMSNavigationType
    val contentType: RaMSContentType

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = RaMSNavigationType.BOTTOM_NAVIGATION
            contentType = RaMSContentType.LIST_ONLY
        }

        WindowWidthSizeClass.Medium -> {
            navigationType = RaMSNavigationType.NAVIGATION_RAIL
            contentType = RaMSContentType.LIST_ONLY
        }

        WindowWidthSizeClass.Expanded -> {
            navigationType = RaMSNavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = RaMSContentType.LIST_AND_DETAIL
        }

        else -> {
            navigationType = RaMSNavigationType.BOTTOM_NAVIGATION
            contentType = RaMSContentType.LIST_ONLY
        }
    }

    val navigationItemContentList = listOf(
        NavigationItemContent(
            charactersListType = RaMSViewModel.CharactersListType.CHARACTERS,
            icon = painterResource(id = state.charactersIconResource),
            text = stringResource(id = R.string.characters)
        ),
        NavigationItemContent(
            charactersListType = RaMSViewModel.CharactersListType.FAVORITES,
            icon = painterResource(id = state.favoritesIconResource),
            text = stringResource(id = R.string.favorite)
        )
    )

    RaMSScreen(
        state = state,
        onSelectCharacter = onSelectCharacter,
        onEnterSearch = onEnterSearch,
        onEnterCharacters = onEnterCharacters,
        onEnterFavorites = onEnterFavorites,
        onAddCharacterToFavorites = onAddCharacterToFavorites,
        onRemoveCharacterFromFavorites = onRemoveCharacterFromFavorites,
        onFilterCharacters = onFilterCharacters,
        navigationType = navigationType,
        contentType = contentType,
        navigationItemContentList = navigationItemContentList
    )
}