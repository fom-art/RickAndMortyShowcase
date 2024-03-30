package com.example.rickyandmortyshowcase.characters.ui

import androidx.compose.foundation.layout.width
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.rickyandmortyshowcase.R
import com.example.rickyandmortyshowcase.characters.ui.viewmodel.CharactersState
import com.example.rickyandmortyshowcase.core.ui.BottomNavigationBar
import com.example.rickyandmortyshowcase.core.ui.NavigationDrawerContent
import com.example.rickyandmortyshowcase.core.ui.NavigationItemContent
import com.example.rickyandmortyshowcase.core.ui.NavigationRail

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
    val bottomNavigationContentDescription = stringResource(R.string.navigation_bottom)
    BottomNavigationBar(
        currentCharacterList = state.currentCharactersList,
        onEnterCharacters = onEnterCharacters,
        onEnterFavorites = onEnterFavorites,
        navigationItemContentList = navigationItemContentList,
        modifier = Modifier.testTag(bottomNavigationContentDescription)
    ) {
        CharactersOneLayoutDisplay(
            state = state,
            onSelectCharacter = onSelectCharacter,
            onEnterSearch = onEnterSearch,
            onEnterCharacters = onEnterCharacters,
            onAddCharacterToFavorites = onAddCharacterToFavorites,
            onRemoveCharacterFromFavorites = onRemoveCharacterFromFavorites,
            onFilterCharacters = onFilterCharacters,
        )
    }
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
    val navigationRailContentDescription = stringResource(id = R.string.navigation_rail)
    NavigationRail(
        currentCharacterList = state.currentCharactersList,
        onEnterCharacters = onEnterCharacters,
        onEnterFavorites = onEnterFavorites,
        navigationItemContentList = navigationItemContentList,
        modifier = Modifier.testTag(navigationRailContentDescription)
    ) {
        CharactersOneLayoutDisplay(
            state = state,
            onSelectCharacter = onSelectCharacter,
            onEnterSearch = onEnterSearch,
            onEnterCharacters = onEnterCharacters,
            onAddCharacterToFavorites = onAddCharacterToFavorites,
            onRemoveCharacterFromFavorites = onRemoveCharacterFromFavorites,
            onFilterCharacters = onFilterCharacters,
        )
    }
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
    val navigationDrawerContentDescription = stringResource(id = R.string.navigation_drawer)
    PermanentNavigationDrawer(
        drawerContent = {
            PermanentDrawerSheet(Modifier.width(dimensionResource(id = R.dimen.drawer_width))) {
                NavigationDrawerContent(
                    currentCharacterList = state.currentCharactersList,
                    onEnterCharacters = onEnterCharacters,
                    onEnterFavorites = onEnterFavorites,
                    navigationItemContentList = navigationItemContentList
                )
            }
        }, modifier = Modifier.testTag(navigationDrawerContentDescription)
    ) {
        CharactersTwoLayoutsDisplay(
            state = state,
            onSelectCharacter = onSelectCharacter,
            onEnterSearch = onEnterSearch,
            onEnterCharacters = onEnterCharacters,
            onAddCharacterToFavorites = onAddCharacterToFavorites,
            onRemoveCharacterFromFavorites = onRemoveCharacterFromFavorites,
            onFilterCharacters = onFilterCharacters,
        )
    }
}