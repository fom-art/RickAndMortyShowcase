package com.example.rickyandmortyshowcase.remote_database_tests

import androidx.lifecycle.ViewModel
import com.example.rickyandmortyshowcase.database.local.domain.FavoritesRepository
import com.example.rickyandmortyshowcase.database.remote.domain.usecases.GetCharacterDetailsUseCase
import com.example.rickyandmortyshowcase.database.remote.domain.usecases.GetCharactersByNameUseCase
import com.example.rickyandmortyshowcase.database.remote.domain.usecases.GetCharactersUseCase
import com.example.rickyandmortyshowcase.ui.RaMSViewModel
import org.junit.Before
import org.mockito.Mockito

class ApolloClientTests {
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
}