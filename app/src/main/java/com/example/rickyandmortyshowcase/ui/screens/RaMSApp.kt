package com.example.rickyandmortyshowcase.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
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
            icon = ImageVector.vectorResource(id = state.charactersIconResource),
            text = stringResource(id = R.string.characters)
        ),
        NavigationItemContent(
            charactersListType = RaMSViewModel.CharactersListType.FAVORITES,
            icon = ImageVector.vectorResource(id = state.charactersIconResource),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RaMSScreen(
    state: RaMSViewModel.RickAndMortyShowcaseState,
    onSelectCharacter: (id: String) -> Unit,
    onEnterSearch: () -> Unit,
    onEnterCharacters: () -> Unit,
    onEnterFavorites: () -> Unit,
    onAddCharacterToFavorites: (id: String) -> Unit,
    onRemoveCharacterFromFavorites: (id: String) -> Unit,
    onFilterCharacters: (name: String) -> Unit,
    navigationType: RaMSNavigationType,
    contentType: RaMSContentType,
    navigationItemContentList: List<NavigationItemContent>
) {
    if (navigationType == RaMSNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        val navigationDrawerContentDesctription = stringResource(id = R.string.navigation_drawer)
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
            },
            modifier = Modifier.testTag(navigationDrawerContentDesctription)
        )
        {
            RaMSAppContent(
                navigationType = navigationType,
                contentType = contentType,
                state = state,
                navigationItemContentList = navigationItemContentList,
                onSelectCharacter = onSelectCharacter,
                onEnterSearch = onEnterSearch,
                onEnterCharacters = onEnterCharacters,
                onEnterFavorites = onEnterFavorites,
                onAddCharacterToFavorites = onAddCharacterToFavorites,
                onRemoveCharacterFromFavorites = onRemoveCharacterFromFavorites,
                onFilterCharacters = onFilterCharacters
            )
        }
    } else {
        if (state.isShowingHomepage) {
            RaMSAppContent(
                navigationType = navigationType,
                contentType = contentType,
                state = state,
                navigationItemContentList = navigationItemContentList,
                onSelectCharacter = onSelectCharacter,
                onEnterSearch = onEnterSearch,
                onEnterCharacters = onEnterCharacters,
                onEnterFavorites = onEnterFavorites,
                onAddCharacterToFavorites = onAddCharacterToFavorites,
                onRemoveCharacterFromFavorites = onRemoveCharacterFromFavorites,
                onFilterCharacters = onFilterCharacters
            )
        } else {
            CharacterDetailsScreen()
        }
    }
}

@Composable
fun RaMSAppContent(
    navigationType: RaMSNavigationType,
    contentType: RaMSContentType,
    state: RaMSViewModel.RickAndMortyShowcaseState,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier,
    onSelectCharacter: (id: String) -> Unit,
    onEnterSearch: () -> Unit,
    onEnterCharacters: () -> Unit,
    onEnterFavorites: () -> Unit,
    onAddCharacterToFavorites: (id: String) -> Unit,
    onRemoveCharacterFromFavorites: (id: String) -> Unit,
    onFilterCharacters: (name: String) -> Unit,
) {
    Box(modifier = modifier) {
        Row(modifier = modifier.fillMaxSize()) {
            val navigationRailContentDescription = stringResource(id = R.string.navigation_rail)
            AnimatedVisibility(visible = navigationType == RaMSNavigationType.NAVIGATION_RAIL) {
                RaMSNavigationRail(
                    currentCharacterList = state.currentCharactersList,
                    onEnterCharacters = onEnterCharacters,
                    onEnterFavorites = onEnterFavorites,
                    navigationItemContentList = navigationItemContentList
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                if (contentType == RaMSContentType.LIST_AND_DETAIL) {
                    RaMSListAndDetailContent(
                        state = state,
                        onSelectCharacter = onSelectCharacter,
                        onEnterSearch = onEnterSearch,
                        onEnterCharacters = onEnterCharacters,
                        onAddCharacterToFavorites = onAddCharacterToFavorites,
                        onRemoveCharacterFromFavorites = onRemoveCharacterFromFavorites,
                        onFilterCharacters = onFilterCharacters
                    )
                } else {
                    RaMSListOnlyContent(
                        state = state,
                        onSelectCharacter = onSelectCharacter,
                        onEnterSearch = onEnterSearch,
                        onEnterCharacters = onEnterCharacters,
                        onAddCharacterToFavorites = onAddCharacterToFavorites,
                        onRemoveCharacterFromFavorites = onRemoveCharacterFromFavorites,
                        onFilterCharacters = onFilterCharacters
                    )
                }
                val bottomNavigationContentDescription = stringResource(R.string.navigation_bottom)
                AnimatedVisibility(visible = navigationType == RaMSNavigationType.BOTTOM_NAVIGATION) {
                    BottomNavigationBar(
                        currentCharacterList = state.currentCharactersList,
                        onEnterCharacters = onEnterCharacters,
                        onEnterFavorites = onEnterFavorites,
                        navigationItemContentList = navigationItemContentList
                    )
                }
            }
        }
    }
}

@Composable
fun RaMSTopBar(
    modifier: Modifier = Modifier
) {
}