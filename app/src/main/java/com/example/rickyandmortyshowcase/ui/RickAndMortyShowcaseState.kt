package com.example.rickyandmortyshowcase.ui

import com.example.rickyandmortyshowcase.R
import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterDetailed
import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterSimple
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

data class RickAndMortyShowcaseState(
    val characters: List<CharacterSimple> = emptyList(),
    val favoriteCharacters: Flow<List<CharacterSimple>> = MutableStateFlow(emptyList()),
    val favoriteCharactersIdList: List<String> = emptyList(),
    val filteredCharacters: List<CharacterSimple> = emptyList(),
    val filter: String = "",
    val currentCharactersList: RaMSViewModel.CharactersListType = RaMSViewModel.CharactersListType.CHARACTERS,
    val isHomepageLoading: Boolean = false,
    val isCharacterDetailsListLoading: Boolean = false,
    val selectedCharacter: CharacterDetailed? = null,
    val isShowingHomepage: Boolean = true,
    val charactersIconResource: Int = R.drawable.characters_selected,
    val favoritesIconResource: Int = R.drawable.favorites_unselected
)