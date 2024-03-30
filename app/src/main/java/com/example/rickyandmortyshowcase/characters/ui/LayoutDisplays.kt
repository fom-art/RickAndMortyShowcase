package com.example.rickyandmortyshowcase.characters.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.rickyandmortyshowcase.characters.ui.layouts.CharacterDetailsEmptyLayout
import com.example.rickyandmortyshowcase.characters.ui.layouts.CharacterDetailsLayout
import com.example.rickyandmortyshowcase.characters.ui.layouts.CharactersListLayout
import com.example.rickyandmortyshowcase.characters.ui.layouts.FavoriteCharactersLayout
import com.example.rickyandmortyshowcase.characters.ui.layouts.FilterCharacterLayout
import com.example.rickyandmortyshowcase.characters.ui.viewmodel.CharactersListType
import com.example.rickyandmortyshowcase.characters.ui.viewmodel.CharactersState

@Composable
fun CharactersOneLayoutDisplay(
    state: CharactersState,
    onSelectCharacter: (id: String) -> Unit,
    onEnterSearch: () -> Unit,
    onEnterCharacters: () -> Unit,
    onAddCharacterToFavorites: (id: String) -> Unit,
    onRemoveCharacterFromFavorites: (id: String) -> Unit,
    onFilterCharacters: (name: String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (state.isShowingHomepage) {
        when (state.currentCharactersList) {
            CharactersListType.CHARACTERS -> {
                CharactersListLayout(
                    state = state,
                    onSelectCharacter = onSelectCharacter,
                    onEnterSearch = onEnterSearch,
                    modifier = modifier
                )
            }

            CharactersListType.FILTER -> {
                FilterCharacterLayout(
                    state = state,
                    onSelectCharacter = onSelectCharacter,
                    onFilterCharacters = onFilterCharacters,
                    onEnterCharacters = onEnterCharacters,
                    modifier = modifier
                )
            }

            CharactersListType.FAVORITES -> {
                FavoriteCharactersLayout(
                    state = state,
                    onSelectCharacter = onSelectCharacter,
                    onEnterCharacters = onEnterCharacters,
                    modifier = modifier
                )
            }
        }
    } else {
        if (!state.isCharacterDetailsListLoading) {
            CharacterDetailsLayout(
                state = state,
                onEnterCharacters = onEnterCharacters,
                onAddCharacterToFavorites = onAddCharacterToFavorites,
                onRemoveCharacterFromFavorites = onRemoveCharacterFromFavorites,
                charactersContentDisplayType = CharactersContentDisplayType.LIST_ONLY,
                modifier = modifier
            )
        } else {
            CircularProgressIndicator(
                modifier = modifier
            )
        }
    }
}

@Composable
fun CharactersTwoLayoutsDisplay(
    state: CharactersState,
    onSelectCharacter: (id: String) -> Unit,
    onEnterSearch: () -> Unit,
    onEnterCharacters: () -> Unit,
    onAddCharacterToFavorites: (id: String) -> Unit,
    onRemoveCharacterFromFavorites: (id: String) -> Unit,
    onFilterCharacters: (name: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxSize()) {
        when (state.currentCharactersList) {
            CharactersListType.CHARACTERS -> {
                CharactersListLayout(
                    state = state,
                    onSelectCharacter = onSelectCharacter,
                    onEnterSearch = onEnterSearch,
                    modifier = Modifier.weight(1f)
                )
            }

            CharactersListType.FILTER -> {
                FilterCharacterLayout(
                    state = state,
                    onSelectCharacter = onSelectCharacter,
                    onFilterCharacters = onFilterCharacters,
                    onEnterCharacters = onEnterCharacters,
                    modifier = Modifier.weight(1f)
                )
            }

            CharactersListType.FAVORITES -> {
                FavoriteCharactersLayout(
                    state = state,
                    onSelectCharacter = onSelectCharacter,
                    onEnterCharacters = onEnterSearch,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            if (state.selectedCharacter != null) {
                CharacterDetailsLayout(
                    state = state,
                    onEnterCharacters = onEnterCharacters,
                    onAddCharacterToFavorites = onAddCharacterToFavorites,
                    onRemoveCharacterFromFavorites = onRemoveCharacterFromFavorites,
                    charactersContentDisplayType = CharactersContentDisplayType.LIST_AND_DETAIL,
                    modifier = modifier
                )
            } else {
                CharacterDetailsEmptyLayout(
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

