package com.example.rickyandmortyshowcase.core.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BlurryBottomShadeRow(content: @Composable (Modifier) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
    ) {
        content(Modifier.fillMaxWidth())
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp) // Adjust the height as needed
                .align(Alignment.BottomCenter)
        ) {
            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color(0x5B000000)),
                    startY = 0f,
                    endY = size.height
                ),
            )
        }
    }
}