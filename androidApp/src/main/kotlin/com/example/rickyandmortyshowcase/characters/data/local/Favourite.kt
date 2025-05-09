package com.example.rickyandmortyshowcase.characters.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favourite(
    @PrimaryKey
    val id: String
)
