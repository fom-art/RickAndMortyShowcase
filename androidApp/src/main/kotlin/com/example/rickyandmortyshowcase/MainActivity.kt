package com.example.rickyandmortyshowcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose.RickyAndMortyShowcaseTheme
import com.example.rickyandmortyshowcase.characters.ui.viewmodel.CharactersViewModel
import com.example.rickyandmortyshowcase.characters.ui.CharactersMainScreen
import dagger.hilt.android.AndroidEntryPoint


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickyAndMortyShowcaseTheme {
                CharactersMainScreen(
                    state = state,
                    windowSize = windowSize.widthSizeClass,
                    onSelectCharacter = viewModel::selectCharacter,
                    onEnterSearch = viewModel::enterSearch,
                    onEnterCharacters = viewModel::enterCharacters,
                    onEnterFavorites = viewModel::enterFavorites,
                    onAddCharacterToFavorites = viewModel::addCharacterToFavorites,
                    onFilterCharacters = viewModel::filterCharacters,
                    onRemoveCharacterFromFavorites = viewModel::removeCharacterFromFavorites
                )
            }
        }
    }
}