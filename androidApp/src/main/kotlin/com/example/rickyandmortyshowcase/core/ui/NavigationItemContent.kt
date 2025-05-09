package com.example.rickyandmortyshowcase.core.ui

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.rickyandmortyshowcase.characters.ui.viewmodel.CharactersListType
import com.example.rickyandmortyshowcase.characters.ui.viewmodel.CharactersViewModel

data class NavigationItemContent(
    val charactersListType: CharactersListType,
    val icon: ImageVector,
    val text: String
)