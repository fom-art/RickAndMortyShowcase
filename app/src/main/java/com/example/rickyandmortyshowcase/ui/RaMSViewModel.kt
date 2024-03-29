package com.example.rickyandmortyshowcase.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickyandmortyshowcase.R
import com.example.rickyandmortyshowcase.domain.CharacterDetailed
import com.example.rickyandmortyshowcase.domain.CharacterSimple
import com.example.rickyandmortyshowcase.domain.DeleteCharacterFromFavouritesUseCase
import com.example.rickyandmortyshowcase.domain.GetCharacterDetailsUseCase
import com.example.rickyandmortyshowcase.domain.GetCharactersByNameUseCase
import com.example.rickyandmortyshowcase.domain.GetCharactersUseCase
import com.example.rickyandmortyshowcase.domain.GetFavouritesUseCase
import com.example.rickyandmortyshowcase.domain.UpsertCharacterToFavouritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RaMSViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
    private val getCharactersByNameUseCase: GetCharactersByNameUseCase,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getFavouritesUseCase: GetFavouritesUseCase,
    private val upsertCharacterToFavouritesUseCase: UpsertCharacterToFavouritesUseCase,
    private val deleteCharacterFromFavouritesUseCase: DeleteCharacterFromFavouritesUseCase
) : ViewModel() {

    private val _ramsState = MutableStateFlow(RaMSState())
    var state = _ramsState.asStateFlow()

    init {
        viewModelScope.launch {
            _ramsState.update {
                it.copy(
                    isHomepageLoading = true
                )
            }
            val idListFlow = getFavouritesUseCase()
            val idList = MutableStateFlow(emptyList<String>())
            idListFlow.asLiveData().observeForever { idList.value = it }
            _ramsState.update {
                val charactersList = getCharactersUseCase()
                it.copy(
                    characters = charactersList,
                    favoriteCharacters = getFavoriteCharactersListFlowFromIdListFlow(
                        idList = idList,
                        charactersList = charactersList
                    ),
                    isHomepageLoading = false
                )
            }
        }
    }

    private fun getFavoriteCharactersListFlowFromIdListFlow(
        idList: Flow<List<String>>,
        charactersList: List<CharacterSimple>
    ): Flow<List<CharacterSimple>> {
        val result = MutableStateFlow(mutableListOf<CharacterSimple>())

        for (id in idList.asLiveData().value!!) {
            val character = charactersList.firstOrNull() { it.id == id }
            result.asLiveData().value!!.add(character!!)
        }

        idList.asLiveData().observeForever {
            result.value.clear()
            for (id in idList.asLiveData().value!!) {
                val character = charactersList.firstOrNull() { it.id == id }
                result.asLiveData().value!!.add(character!!)
            }
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

    internal suspend fun getCharacterDetailsFromId(id:String): CharacterDetailed? {
        return getCharacterDetailsUseCase(id)
    }
}