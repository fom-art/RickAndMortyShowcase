package com.fomart.rms.shared.feature.catalog.favorite.browse.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.fomart.rms.shared.feature.catalog.favorite.browse.favorites.FavoritesRoute
import kotlinx.serialization.Serializable

@Serializable
data object FavoritesScreen

fun NavController.navigateToFavorites(
    navOptions: NavOptions? = null
) = navigate(FavoritesScreen, navOptions)

fun NavGraphBuilder.favoritesScreen() {
    composable<FavoritesScreen> {
        FavoritesRoute()
    }
}
