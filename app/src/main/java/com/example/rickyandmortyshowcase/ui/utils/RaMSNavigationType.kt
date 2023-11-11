package com.example.rickyandmortyshowcase.ui.utils

import androidx.compose.ui.graphics.vector.Path
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.painterResource
import com.example.rickyandmortyshowcase.R
import java.nio.file.Path

enum class RaMSNavigationType{
    BOTTOM_NAVIGATION, NAVIGATION_RAIL, PERMANENT_NAVIGATION_DRAWER
}

enum class RaMSContentType{
    LIST_ONLY, LIST_AND_DETAIL
}