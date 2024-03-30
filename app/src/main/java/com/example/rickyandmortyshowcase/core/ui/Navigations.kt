package com.example.rickyandmortyshowcase.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import com.example.rickyandmortyshowcase.R
import com.example.rickyandmortyshowcase.characters.ui.viewmodel.CharactersListType
import com.example.rickyandmortyshowcase.characters.ui.viewmodel.CharactersViewModel

@Composable
fun NavigationDrawerContent(
    currentCharacterList: CharactersListType,
    onEnterCharacters: () -> Unit,
    onEnterFavorites: () -> Unit,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier,
) {
    Row {
        Column(
            modifier = modifier
                .wrapContentWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.inverseOnSurface)
                .padding(dimensionResource(id = R.dimen.drawer_padding_content))
        ) {
            for (navItem in navigationItemContentList) {
                NavigationDrawerItem(
                    label = {
                        Text(
                            text = navItem.text,
                            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.drawer_padding_content)),
                            color =
                            if (currentCharacterList == navItem.charactersListType) colorResource(
                                id = R.color.selected_icon_color
                            ) else colorResource(
                                id = R.color.unselected_icon_color
                            )
                        )
                    },
                    selected = currentCharacterList == navItem.charactersListType,
                    onClick = {
                        when (navItem.charactersListType) {
                            CharactersListType.CHARACTERS -> onEnterCharacters()
                            CharactersListType.FAVORITES -> onEnterFavorites()
                            else -> onEnterCharacters()
                        }
                    },
                    icon = {
                        Image(
                            imageVector = navItem.icon,
                            contentDescription = navItem.text
                        )
                    })
            }
        }
    }
}

@Composable
fun NavigationRail(
    currentCharacterList: CharactersListType,
    onEnterCharacters: () -> Unit,
    onEnterFavorites: () -> Unit, navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Row {
        androidx.compose.material3.NavigationRail(modifier = modifier) {
            for (navItem in navigationItemContentList) {
                NavigationRailItem(
                    selected = currentCharacterList == navItem.charactersListType,
                    onClick = {
                        when (navItem.charactersListType) {
                            CharactersListType.CHARACTERS -> onEnterCharacters()
                            CharactersListType.FAVORITES -> onEnterFavorites()
                            else -> onEnterCharacters()
                        }
                    },
                    icon = {
                        Image(
                            imageVector = navItem.icon,
                            contentDescription = navItem.text
                        )
                    }
                )
            }
        }
        content()
    }
}

@Composable
fun BottomNavigationBar(
    currentCharacterList: CharactersListType,
    onEnterCharacters: () -> Unit,
    onEnterFavorites: () -> Unit,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column {
        Box(modifier = Modifier.weight(1f))
        { content() }
        NavigationBar(modifier = modifier) {
            for (navItem in navigationItemContentList) {
                NavigationBarItem(
                    selected = currentCharacterList == navItem.charactersListType,
                    onClick = {
                        when (navItem.charactersListType) {
                            CharactersListType.CHARACTERS -> onEnterCharacters()
                            CharactersListType.FAVORITES -> onEnterFavorites()
                            else -> onEnterCharacters()
                        }
                    },
                    icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                imageVector = navItem.icon,
                                contentDescription = navItem.text
                            )
                            Text(
                                text = navItem.text,
                                style = MaterialTheme.typography.labelSmall,
                                color =
                                if (currentCharacterList == navItem.charactersListType) colorResource(
                                    id = R.color.selected_icon_color
                                ) else colorResource(
                                    id = R.color.unselected_icon_color
                                )
                            )
                        }
                    },

                    )
            }
        }
    }
}