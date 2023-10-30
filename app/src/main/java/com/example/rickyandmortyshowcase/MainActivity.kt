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
import com.example.rickyandmortyshowcase.ui.RaMSViewModel
import com.example.rickyandmortyshowcase.ui.screens.RaMSApp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickyAndMortyShowcaseTheme {
                Surface {
                    val viewModel = hiltViewModel<RaMSViewModel>()
                    val state by viewModel.state.collectAsState()
                    val windowSize = calculateWindowSizeClass(activity = this)
                    RaMSApp(
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
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@Preview
@Composable
fun GreetingPreview() {
    RickyAndMortyShowcaseTheme {
        Greeting("Android")
    }
}