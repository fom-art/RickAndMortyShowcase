package com.example.rickyandmortyshowcase.ui.utils

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.rickyandmortyshowcase.ui.RaMSViewModel

data class NavigationItemContent(
    val charactersListType: RaMSViewModel.CharactersListType,
    val icon: ImageVector,
    val text: String
)