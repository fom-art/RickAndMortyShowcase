package com.example.rickyandmortyshowcase.data

import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterSimple

val allCharacter: List<CharacterSimple> = listOf(
    CharacterSimple("1", "Rick Sanchez", "Alive", "https://rickandmortyapi.com/api/character/avatar/1.jpeg"),
    CharacterSimple("2", "Morty Smith", "Alive", "https://rickandmortyapi.com/api/character/avatar/2.jpeg"),
    CharacterSimple("3", "Summer Smith", "Alive", "https://rickandmortyapi.com/api/character/avatar/3.jpeg"),
    CharacterSimple("4", "Beth Smith", "Alive", "https://rickandmortyapi.com/api/character/avatar/4.jpeg"),
    CharacterSimple("5", "Jerry Smith", "Alive", "https://rickandmortyapi.com/api/character/avatar/5.jpeg"),
    CharacterSimple("6", "Abadango Cluster Princess", "Alive", "https://rickandmortyapi.com/api/character/avatar/6.jpeg"),
    CharacterSimple("7", "Abradolf Lincler", "unknown", "https://rickandmortyapi.com/api/character/avatar/7.jpeg"),
    CharacterSimple("8", "Adjudicator Rick", "Dead", "https://rickandmortyapi.com/api/character/avatar/8.jpeg"),
    CharacterSimple("9", "Agency Director", "Dead", "https://rickandmortyapi.com/api/character/avatar/9.jpeg"),
    CharacterSimple("10", "Alan Rails", "Dead", "https://rickandmortyapi.com/api/character/avatar/10.jpeg"),
)

val favoritesList: List<String> = listOf(
    "2",
    "4",
    "7"
)