package com.fomart.rms.shared.feature.character_details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.fomart.rms.shared.feature.character_details.presentation.CharacterDetailsRoute
import com.fomart.rms.shared.feature.character_details.presentation.CharacterDetailsViewModel
import kotlinx.serialization.Serializable
import org.koin.compose.koinInject

@Serializable
data class CharacterDetailsScreen(val id: String)

fun NavController.navigateToCharacterDetails(
    id: String,
    navOptions: NavOptions? = null
) = navigate(CharacterDetailsScreen(id), navOptions)

fun NavGraphBuilder.characterDetailsScreen(
    navigateBack: () -> Unit,
) {
    composable<CharacterDetailsScreen> {
        val args = it.toRoute<CharacterDetailsScreen>()
        CharacterDetailsRoute(
            navigateBack = navigateBack,
            characterId = args.id
        )
    }
}

