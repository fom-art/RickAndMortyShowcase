package com.example.rickyandmortyshowcase.data.local.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
    @PrimaryKey
    val id: String
)
