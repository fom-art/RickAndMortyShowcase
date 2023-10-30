package com.example.rickyandmortyshowcase.database.local.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favourite(
    @PrimaryKey
    val id: String
)
