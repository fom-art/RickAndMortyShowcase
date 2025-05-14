package com.fomart.mafiamaster.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import com.fomart.rms.core.model.ui.UiImageVector
import com.fomart.rms.core.model.ui.UiText
import com.fomart.rms.shared.feature.catalog.navigation.CatalogScreen
import com.fomart.rms.shared.feature.favorites.navigation.FavoritesScreen
import rickandmortyshowcase.shared.app.generated.resources.Res
import rickandmortyshowcase.shared.app.generated.resources.characters
import rickandmortyshowcase.shared.app.generated.resources.favorite
import rickandmortyshowcase.shared.app.generated.resources.ic_characters
import kotlin.reflect.KClass

sealed class TopLevelDestination(
    val selectedIcon: UiImageVector,
    val unselectedIcon: UiImageVector,
    val iconUiText: UiText,
    val screenRoute: KClass<*>,
) {
    object Characters : TopLevelDestination(
        selectedIcon = UiImageVector.ImageVectorResourceId(Res.drawable.ic_characters),
        unselectedIcon = UiImageVector.ImageVectorResourceId(Res.drawable.ic_characters),
        iconUiText = UiText.StringResourceId(Res.string.characters),
        screenRoute = CatalogScreen::class,
    )

    object Favorites : TopLevelDestination(
        selectedIcon = UiImageVector.DynamicImageVector(Icons.Default.Star),
        unselectedIcon = UiImageVector.DynamicImageVector(Icons.Filled.Star),
        iconUiText = UiText.StringResourceId(Res.string.favorite),
        screenRoute = FavoritesScreen::class,
    )

    companion object {
        val entries = listOf(Characters, Favorites)
    }
}
