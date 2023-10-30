package com.example.rickyandmortyshowcase.database.local.domain

import com.example.rickyandmortyshowcase.database.local.data.Favorite

data class FavoriteState(
    val favorites: List<Favorite> = emptyList()
)
