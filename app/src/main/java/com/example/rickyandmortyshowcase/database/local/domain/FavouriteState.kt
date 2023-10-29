package com.example.rickyandmortyshowcase.database.local.domain

import com.example.rickyandmortyshowcase.database.local.data.Favourite

data class FavouriteState(
    val favourites: List<Favourite> = emptyList()
)
