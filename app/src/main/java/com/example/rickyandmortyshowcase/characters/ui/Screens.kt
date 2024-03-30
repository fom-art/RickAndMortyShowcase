package com.example.rickyandmortyshowcase.characters.ui

import androidx.compose.runtime.Composable
import com.example.rickyandmortyshowcase.characters.ui.viewmodel.CharactersState
import com.example.rickyandmortyshowcase.core.ui.NavigationType
import com.example.rickyandmortyshowcase.core.ui.NavigationItemContent

@Composable
fun CompactScreen(
    state: CharactersState,
    onSelectCharacter: (id: String) -> Unit,
    onEnterSearch: () -> Unit,
    onEnterCharacters: () -> Unit,
    onEnterFavorites: () -> Unit,
    onAddCharacterToFavorites: (id: String) -> Unit,
    onRemoveCharacterFromFavorites: (id: String) -> Unit,
    onFilterCharacters: (name: String) -> Unit,
    navigationItemContentList: List<NavigationItemContent>
) {
    val navigationType = NavigationType.BOTTOM_NAVIGATION
    val charactersScreenContentDisplayType = CharactersScreenContentDisplayType.LIST_ONLY
    Screen(
        state = state,
        onSelectCharacter = onSelectCharacter,
        onEnterSearch = onEnterSearch,
        onEnterCharacters = onEnterCharacters,
        onEnterFavorites = onEnterFavorites,
        onAddCharacterToFavorites = onAddCharacterToFavorites,
        onRemoveCharacterFromFavorites = onRemoveCharacterFromFavorites,
        onFilterCharacters = onFilterCharacters,
        navigationType = navigationType,
        charactersScreenContentDisplayType = charactersScreenContentDisplayType,
        navigationItemContentList = navigationItemContentList
    )
}

@Composable
fun MediumScreen(
    state: CharactersState,
    onSelectCharacter: (id: String) -> Unit,
    onEnterSearch: () -> Unit,
    onEnterCharacters: () -> Unit,
    onEnterFavorites: () -> Unit,
    onAddCharacterToFavorites: (id: String) -> Unit,
    onRemoveCharacterFromFavorites: (id: String) -> Unit,
    onFilterCharacters: (name: String) -> Unit,
    navigationItemContentList: List<NavigationItemContent>
) {
    val navigationType = NavigationType.NAVIGATION_RAIL
    val charactersScreenContentDisplayType = CharactersScreenContentDisplayType.LIST_ONLY
    Screen(
        state = state,
        onSelectCharacter = onSelectCharacter,
        onEnterSearch = onEnterSearch,
        onEnterCharacters = onEnterCharacters,
        onEnterFavorites = onEnterFavorites,
        onAddCharacterToFavorites = onAddCharacterToFavorites,
        onRemoveCharacterFromFavorites = onRemoveCharacterFromFavorites,
        onFilterCharacters = onFilterCharacters,
        navigationType = navigationType,
        charactersScreenContentDisplayType = charactersScreenContentDisplayType,
        navigationItemContentList = navigationItemContentList
    )
}

@Composable
fun ExpandedScreen(
    state: CharactersState,
    onSelectCharacter: (id: String) -> Unit,
    onEnterSearch: () -> Unit,
    onEnterCharacters: () -> Unit,
    onEnterFavorites: () -> Unit,
    onAddCharacterToFavorites: (id: String) -> Unit,
    onRemoveCharacterFromFavorites: (id: String) -> Unit,
    onFilterCharacters: (name: String) -> Unit,
    navigationItemContentList: List<NavigationItemContent>
) {
    val navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER
    val charactersScreenContentDisplayType = CharactersScreenContentDisplayType.LIST_AND_DETAIL
    Screen(
        state = state,
        onSelectCharacter = onSelectCharacter,
        onEnterSearch = onEnterSearch,
        onEnterCharacters = onEnterCharacters,
        onEnterFavorites = onEnterFavorites,
        onAddCharacterToFavorites = onAddCharacterToFavorites,
        onRemoveCharacterFromFavorites = onRemoveCharacterFromFavorites,
        onFilterCharacters = onFilterCharacters,
        navigationType = navigationType,
        charactersScreenContentDisplayType = charactersScreenContentDisplayType,
        navigationItemContentList = navigationItemContentList
    )
}