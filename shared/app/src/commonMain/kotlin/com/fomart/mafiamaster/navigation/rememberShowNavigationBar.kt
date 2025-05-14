package com.fomart.mafiamaster.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.fomart.mafiamaster.navigation.TopLevelDestination
import kotlinx.coroutines.delay

@Composable
fun rememberShowNavigationBar(currentTopLevelDestination: TopLevelDestination?): Boolean {
    var debouncedShowNavigationBar by remember { mutableStateOf(currentTopLevelDestination != null) }
    var target by remember { mutableStateOf(currentTopLevelDestination != null) }

    // Watch for changes
    LaunchedEffect(currentTopLevelDestination) {
        val newValue = currentTopLevelDestination != null
        if (newValue != target) {
            target = newValue
            //Make a little delay, so that navigation bar didn't blink when changing screens
            delay(50)
            debouncedShowNavigationBar = target
        }
    }

    return debouncedShowNavigationBar
}