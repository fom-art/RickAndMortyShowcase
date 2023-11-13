package com.example.rickyandmortyshowcase.viewmodel_tests

import androidx.lifecycle.ViewModel
import com.example.rickyandmortyshowcase.database.local.domain.FavoritesRepository
import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterDetailed
import com.example.rickyandmortyshowcase.database.remote.domain.usecases.GetCharacterDetailsUseCase
import com.example.rickyandmortyshowcase.database.remote.domain.usecases.GetCharactersByNameUseCase
import com.example.rickyandmortyshowcase.database.remote.domain.usecases.GetCharactersUseCase
import com.example.rickyandmortyshowcase.ui.RaMSViewModel
import org.junit.Before
import org.mockito.Mockito


class RaMSViewModelTest {
    private lateinit var viewModel: ViewModel
    private lateinit var getCharacterDetailsUseCase: GetCharacterDetailsUseCase
    private lateinit var getCharactersByNameUseCase: GetCharactersByNameUseCase
    private lateinit var getCharactersUseCase: GetCharactersUseCase
    private lateinit var favoritesRepository: FavoritesRepository

    @Before
    fun init() {
        getCharacterDetailsUseCase = Mockito.mock(GetCharacterDetailsUseCase::class.java)
        getCharactersByNameUseCase = Mockito.mock(GetCharactersByNameUseCase::class.java)
        getCharactersUseCase = Mockito.mock(GetCharactersUseCase::class.java)
        favoritesRepository = Mockito.mock(FavoritesRepository::class.java)
        viewModel = RaMSViewModel(
            getCharacterDetailsUseCase = getCharacterDetailsUseCase,
            getCharactersByNameUseCase = getCharactersByNameUseCase,
            getCharactersUseCase = getCharactersUseCase,
            favoritesRepository = favoritesRepository
        )
    }

//    @Test
//    fun ramsViewModel_selectCharacter_rightCharacterSelected() {
//        runBlocking { setupMocksForCharacterSelection() }
//    }
//
//    private suspend fun setupMocksForCharacterSelection() {
//        val nameCaptor = ArgumentCaptor.forClass(String::class.java)
//
//        `when`(getCharacterDetailsUseCase.execute(name = nameCaptor.capture())).thenReturn(
//            CharacterDetailed(name = nameCaptor.value))
//    }
}