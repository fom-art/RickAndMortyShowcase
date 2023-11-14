package com.example.rickyandmortyshowcase.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickyandmortyshowcase.R
import com.example.rickyandmortyshowcase.database.local.data.Favorite
import com.example.rickyandmortyshowcase.database.local.domain.FavoritesRepository
import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterDetailed
import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterSimple
import com.example.rickyandmortyshowcase.database.remote.domain.usecases.GetCharacterDetailsUseCase
import com.example.rickyandmortyshowcase.database.remote.domain.usecases.GetCharactersByNameUseCase
import com.example.rickyandmortyshowcase.database.remote.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

@HiltViewModel
class RaMSViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
    private val getCharactersByNameUseCase: GetCharactersByNameUseCase,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val _ramsState = MutableStateFlow(RaMSState())
    var state = _ramsState.asStateFlow()
    private var filterCharactersJob: Job? = null

    init {
        viewModelScope.launch {
            _ramsState.update {
                it.copy(
                    isHomepageLoading = true
                )
            }
            val idListFlow = favoritesRepository.getFavourites()
            val idList = MutableStateFlow(emptyList<String>())
            idListFlow.asLiveData().observeForever { idList.value = it }
            _ramsState.update {
                val charactersList = getCharactersUseCase.execute()
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
        // Cancel the previous job if it exists
        filterCharactersJob?.cancel()

        // Create a new job for the current call
        filterCharactersJob = viewModelScope.launch {
            try {
                _ramsState.update {
                    it.copy(
                        filter = name,
                        isHomepageLoading = true
                    )
                }

                // Simulate some asynchronous work with delay
                delay(1000)

                val filteredCharacters = withTimeout(5000) {
                    getCharactersByNameUseCase.execute(name)
                }

                _ramsState.update {
                    it.copy(
                        filteredCharacters = filteredCharacters,
                        isHomepageLoading = false
                    )
                }
            } catch (e: TimeoutCancellationException) {
                _ramsState.update {
                    it.copy(
                        isHomepageLoading = false,
                    )
                }
            }
        }
    }

    fun addCharacterToFavorites(id: String) {
        viewModelScope.launch {
            favoritesRepository.upsertCharacter(Favorite(id))
        }
    }

    fun removeCharacterFromFavorites(id: String) {
        viewModelScope.launch {
            favoritesRepository.deleteCharacter(Favorite(id))
        }
    }

    enum class CharactersListType {
        CHARACTERS, FAVORITES, FILTER
    }

    internal suspend fun getCharacterDetailsFromId(id:String): CharacterDetailed? {
        return getCharacterDetailsUseCase.execute(id)
    }
}