package com.example.rickyandmortyshowcase.ui.utils

import androidx.compose.ui.graphics.painter.Painter
import com.example.rickyandmortyshowcase.ui.RaMSViewModel

data class NavigationItemContent(
    val charactersListType: RaMSViewModel.CharactersListType,
    val icon: Painter,
    val text: String
)