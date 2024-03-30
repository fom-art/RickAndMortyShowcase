package com.example.rickyandmortyshowcase.characters.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickyandmortyshowcase.R
import com.example.rickyandmortyshowcase.characters.domain.CharacterDetailed
import com.example.rickyandmortyshowcase.characters.domain.CharacterSimple
import com.example.rickyandmortyshowcase.characters.domain.DeleteCharacterFromFavouritesUseCase
import com.example.rickyandmortyshowcase.characters.domain.GetCharacterDetailsUseCase
import com.example.rickyandmortyshowcase.characters.domain.GetCharactersByNameUseCase
import com.example.rickyandmortyshowcase.characters.domain.GetCharactersUseCase
import com.example.rickyandmortyshowcase.characters.domain.GetFavouritesUseCase
import com.example.rickyandmortyshowcase.characters.domain.UpsertCharacterToFavouritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
    private val getCharactersByNameUseCase: GetCharactersByNameUseCase,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getFavouritesUseCase: GetFavouritesUseCase,
    private val upsertCharacterToFavouritesUseCase: UpsertCharacterToFavouritesUseCase,
    private val deleteCharacterFromFavouritesUseCase: DeleteCharacterFromFavouritesUseCase
) : ViewModel() {

    private val _ramsState = MutableStateFlow(CharactersState())
    var state = _ramsState.asStateFlow()

    init {
        viewModelScope.launch {
            _ramsState.update {
                it.copy(
                    isHomepageLoading = true
                )
            }

            try {
                val idList: List<String> = getFavouritesUseCase().firstOrNull() ?: emptyList()
                _ramsState.update {
                    val charactersList = getCharactersUseCase()
                    it.copy(
                        characters = charactersList,
                        favoriteCharacters = getFavoriteCharactersListFromIdList(
                            idList = idList,
                            charactersList = charactersList
                        ),
                        isHomepageLoading = false
                    )
                }
            } catch (e: Exception) {
                e.message?.let { Log.e("ViewModel", it) }
            }
        }
    }

    private fun getFavoriteCharactersListFromIdList(
        idList: List<String>,
        charactersList: List<CharacterSimple>
    ): List<CharacterSimple> {
        val result = mutableListOf<CharacterSimple>()

        for (id in idList) {
            val character = charactersList.firstOrNull() { it.id == id }
            result.add(character!!)
        }

        return result
    }

    fun selectCharacter(id: String) {
        viewModelScope.launch {
            _ramsState.update {
                it.copy(
                    isShowingHomepage = false,
                    isCharacterDetailsListLoading = true
                )
            }
            _ramsState.update {
                it.copy(
                    selectedCharacter = getCharacterDetailsFromId(id),
                    isCharacterDetailsListLoading = false
                )
            }
        }
    }

    fun enterSearch() {
        _ramsState.update {
            it.copy(
                isShowingHomepage = true,
                currentCharactersList = CharactersListType.FILTER,
                filteredCharacters = emptyList()
            )
        }
    }

    fun enterFavorites() {
        _ramsState.update {
            it.copy(
                isShowingHomepage = true,
                currentCharactersList = CharactersListType.FAVORITES,
                charactersIconResource = R.drawable.characters_unselected,
                favoritesIconResource = R.drawable.favorites_selected
            )
        }
    }

    fun enterCharacters() {
        _ramsState.update {
            it.copy(
                isShowingHomepage = true,
                currentCharactersList = CharactersListType.CHARACTERS,
                charactersIconResource = R.drawable.characters_selected,
                favoritesIconResource = R.drawable.favorites_unselected
            )
        }
    }

    fun filterCharacters(name: String) {
        viewModelScope.launch {
            _ramsState.update {
                it.copy(
                    filter = name,
                    isHomepageLoading = true
                )
            }
            _ramsState.update {
                it.copy(
                    filteredCharacters = getCharactersByNameUseCase(name),
                    isHomepageLoading = false
                )
            }
        }
    }

    fun addCharacterToFavorites(id: String) {
        viewModelScope.launch {
            upsertCharacterToFavouritesUseCase(id)
        }
    }

    fun removeCharacterFromFavorites(id: String) {
        viewModelScope.launch {
            deleteCharacterFromFavouritesUseCase(id)
        }
    }

    enum class CharactersListType {
        CHARACTERS, FAVORITES, FILTER
    }

    internal suspend fun getCharacterDetailsFromId(id: String): CharacterDetailed? {
        return getCharacterDetailsUseCase(id)
    }
}