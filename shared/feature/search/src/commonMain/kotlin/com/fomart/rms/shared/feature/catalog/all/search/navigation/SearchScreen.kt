package com.fomart.rms.shared.feature.catalog.all.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.fomart.rms.shared.feature.catalog.all.search.presentation.SearchRoute
import kotlinx.serialization.Serializable

@Serializable
data object SearchScreen

fun NavController.navigateToSearch(
    navOptions: NavOptions? = null
) = navigate(SearchScreen, navOptions)

fun NavGraphBuilder.favoritesScreen(
    navigateToCharacterDetails: (String) -> Unit,
    navigateBack: () -> Unit
) {
    composable<SearchScreen> {
        SearchRoute(
            navigateToCharacterDetails = navigateToCharacterDetails,
            navigateBack = navigateBack
        )
    }
}
