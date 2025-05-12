package com.fomart.rms.shared.feature.favorites.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.fomart.rms.shared.feature.favorites.presentation.FavoritesRoute
import kotlinx.serialization.Serializable

@Serializable
data object FavoritesScreen

fun NavController.navigateToFavorites(
    navOptions: NavOptions? = null
) = navigate(FavoritesScreen, navOptions)

fun NavGraphBuilder.favoritesScreen(
    navigateToCharacterDetails: (String) -> Unit
) {
    composable<FavoritesScreen> {
        FavoritesRoute(navigateToCharacterDetails = navigateToCharacterDetails)
    }
}
