package com.example.rickyandmortyshowcase.ui.screens

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import com.example.rickyandmortyshowcase.ui.RaMSViewModel
import com.example.rickyandmortyshowcase.ui.utils.NavigationItemContent
import com.example.rickyandmortyshowcase.ui.utils.RaMSContentType
import com.example.rickyandmortyshowcase.ui.utils.RaMSNavigationType

@Composable
fun RaMSScreen(
    state: RaMSViewModel.RickAndMortyShowcaseState,
    onSelectCharacter: (id: String) -> Unit,
    onEnterSearch: () -> Unit,
    onEnterCharacters: () -> Unit,
    onEnterFavorites: () -> Unit,
    onAddCharacterToFavorites: (id: String) -> Unit,
    onRemoveCharacterFromFavorites: (id: String) -> Unit,
    onFilterCharacters: (name: String) -> Unit,
    navigationType: RaMSNavigationType,
    contentType: RaMSContentType,
    navigationItemContentList: List<NavigationItemContent>
) {

}