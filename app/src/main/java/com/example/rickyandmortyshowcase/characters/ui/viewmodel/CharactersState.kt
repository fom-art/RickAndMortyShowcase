package com.example.rickyandmortyshowcase.characters.ui.viewmodel

import com.example.rickyandmortyshowcase.R
import com.example.rickyandmortyshowcase.characters.domain.CharacterDetailed
import com.example.rickyandmortyshowcase.characters.domain.CharacterSimple

data class CharactersState(
    val characters: List<CharacterSimple> = emptyList(),
    val favoriteCharacters: List<CharacterSimple> = emptyList(),
    val favoriteCharactersIdList: List<String> = emptyList(),
    val filteredCharacters: List<CharacterSimple> = emptyList(),
    val filter: String = "",
    val currentCharactersList: CharactersListType = CharactersListType.CHARACTERS,
    val isHomepageLoading: Boolean = false,
    val isCharacterDetailsListLoading: Boolean = false,
    val selectedCharacter: CharacterDetailed? = null,
    val isShowingHomepage: Boolean = true,
    val charactersIconResource: Int = R.drawable.characters_selected,
    val favoritesIconResource: Int = R.drawable.favorites_unselected
)