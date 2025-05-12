package com.fomart.mafiamaster.navigation

import AppStateStore
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.fomart.rms.shared.feature.catalog.all.search.navigation.searchScreen
import com.fomart.rms.shared.feature.catalog.navigation.CatalogScreen
import com.fomart.rms.shared.feature.catalog.navigation.catalogScreen
import com.fomart.rms.shared.feature.character_details.navigation.characterDetailsScreen
import com.fomart.rms.shared.feature.character_details.navigation.navigateToCharacterDetails
import com.fomart.rms.shared.feature.favorites.navigation.favoritesScreen

@Composable
fun MafiaMasterNavHost(
    modifier: Modifier = Modifier,
    appState: AppStateStore,
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    val navController = appState.navController

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = CatalogScreen,
    ) {
        catalogScreen(
            navigateToCharacterDetails = navController::navigateToCharacterDetails
        )
        characterDetailsScreen()
        favoritesScreen(
            navigateToCharacterDetails = navController::navigateToCharacterDetails
        )
        searchScreen(
            navigateToCharacterDetails = navController::navigateToCharacterDetails,
            navigateBack = navController::navigateUp
        )
    }
}