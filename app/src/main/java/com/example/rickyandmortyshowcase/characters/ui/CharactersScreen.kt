package com.example.rickyandmortyshowcase.characters.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.rickyandmortyshowcase.R
import com.example.rickyandmortyshowcase.characters.ui.viewmodel.CharactersState
import com.example.rickyandmortyshowcase.characters.ui.viewmodel.CharactersViewModel
import com.example.rickyandmortyshowcase.core.ui.NavigationType
import com.example.rickyandmortyshowcase.core.ui.BottomNavigationBar
import com.example.rickyandmortyshowcase.core.ui.NavigationDrawerContent
import com.example.rickyandmortyshowcase.core.ui.NavigationItemContent
import com.example.rickyandmortyshowcase.core.ui.NavigationRail

@Composable
fun CharactersScreen(
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
    val navigationType: NavigationType
    val charactersScreenContentDisplayType: CharactersScreenContentDisplayType
    val navigationItemContentList = listOf(
        NavigationItemContent(
            charactersListType = CharactersViewModel.CharactersListType.CHARACTERS,
            icon = ImageVector.vectorResource(id = state.charactersIconResource),
            text = stringResource(id = R.string.characters)
        ), NavigationItemContent(
            charactersListType = CharactersViewModel.CharactersListType.FAVORITES,
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(
    state: CharactersState,
    onSelectCharacter: (id: String) -> Unit,
    onEnterSearch: () -> Unit,
    onEnterCharacters: () -> Unit,
    onEnterFavorites: () -> Unit,
    onAddCharacterToFavorites: (id: String) -> Unit,
    onRemoveCharacterFromFavorites: (id: String) -> Unit,
    onFilterCharacters: (name: String) -> Unit,
    navigationType: NavigationType,
    charactersScreenContentDisplayType: CharactersScreenContentDisplayType,
    navigationItemContentList: List<NavigationItemContent>
) {
    if (navigationType == NavigationType.PERMANENT_NAVIGATION_DRAWER) {
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
            AppContent(
                navigationType = navigationType,
                charactersScreenContentDisplayType = charactersScreenContentDisplayType,
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
        AppContent(
            navigationType = navigationType,
            charactersScreenContentDisplayType = charactersScreenContentDisplayType,
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
}

@Composable
fun AppContent(
    navigationType: NavigationType,
    charactersScreenContentDisplayType: CharactersScreenContentDisplayType,
    state: CharactersState,
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
            AnimatedVisibility(visible = navigationType == NavigationType.NAVIGATION_RAIL) {
                NavigationRail(
                    currentCharacterList = state.currentCharactersList,
                    onEnterCharacters = onEnterCharacters,
                    onEnterFavorites = onEnterFavorites,
                    navigationItemContentList = navigationItemContentList,
                    modifier = Modifier.testTag(navigationRailContentDescription)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                if (charactersScreenContentDisplayType == CharactersScreenContentDisplayType.LIST_AND_DETAIL) {
                    RaMSListAndDetailContent(
                        state = state,
                        onSelectCharacter = onSelectCharacter,
                        onEnterSearch = onEnterSearch,
                        onEnterCharacters = onEnterCharacters,
                        onAddCharacterToFavorites = onAddCharacterToFavorites,
                        onRemoveCharacterFromFavorites = onRemoveCharacterFromFavorites,
                        onFilterCharacters = onFilterCharacters,
                        modifier = modifier.weight(1f)
                    )
                } else {
                    RaMSListOnlyContent(
                        state = state,
                        onSelectCharacter = onSelectCharacter,
                        onEnterSearch = onEnterSearch,
                        onEnterCharacters = onEnterCharacters,
                        onAddCharacterToFavorites = onAddCharacterToFavorites,
                        onRemoveCharacterFromFavorites = onRemoveCharacterFromFavorites,
                        onFilterCharacters = onFilterCharacters,
                        charactersScreenContentDisplayType = charactersScreenContentDisplayType,
                        modifier = modifier.weight(1f)
                    )
                }
                val bottomNavigationContentDescription = stringResource(R.string.navigation_bottom)
                AnimatedVisibility(
                    visible = navigationType == NavigationType.BOTTOM_NAVIGATION &&
                            state.isShowingHomepage && state.currentCharactersList != CharactersViewModel.CharactersListType.FILTER
                ) {
                    BottomNavigationBar(
                        currentCharacterList = state.currentCharactersList,
                        onEnterCharacters = onEnterCharacters,
                        onEnterFavorites = onEnterFavorites,
                        navigationItemContentList = navigationItemContentList,
                        modifier = Modifier.testTag(bottomNavigationContentDescription)
                    )
                }
            }
        }
    }
}

@Composable
fun CharactersListTopBar(
    onEnterSearch: () -> Unit, modifier: Modifier = Modifier
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
                    text = stringResource(id = R.string.characters),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.top_bar_padding_horizontal))
                )
                IconButton(
                    onClick = onEnterSearch, modifier = Modifier
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.search_characters)
                    )
                }
            }
        }
    }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterCharactersTopBar(
    state: CharactersState,
    onEnterCharacters: () -> Unit,
    onFilterCharacters: (name: String) -> Unit,
    modifier: Modifier = Modifier,
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
                IconButton(onClick = onEnterCharacters) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.enter_characters_list)
                    )
                }
                TextField(
                    value = state.filter,
                    label = {
                        Text(
                            text = stringResource(id = R.string.search_characters),
                            style = MaterialTheme.typography.displayMedium,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    },
                    onValueChange = onFilterCharacters,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = dimensionResource(R.dimen.filter_text_input_padding_start))
                )
            }
        }
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

@Composable
fun BlurryBottomShadeRow(content: @Composable (Modifier) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
    ) {
        content(Modifier.fillMaxWidth())
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp) // Adjust the height as needed
                .align(Alignment.BottomCenter)
        ) {
            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color(0x5B000000)),
                    startY = 0f,
                    endY = size.height
                ),
            )
        }
    }
}