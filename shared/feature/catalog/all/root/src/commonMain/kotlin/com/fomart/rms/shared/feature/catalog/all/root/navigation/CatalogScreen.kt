package com.fomart.rms.shared.feature.catalog.all.root.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.fomart.rms.shared.feature.catalog.all.root.presentation.CatalogRoute
import kotlinx.serialization.Serializable

@Serializable
data object CatalogScreen

fun NavController.navigateToCatalog(
    navOptions: NavOptions? = null
) = navigate(CatalogScreen, navOptions)

fun NavGraphBuilder.catalogScreen() {
    composable<CatalogScreen> {
        CatalogRoute()
    }
}
