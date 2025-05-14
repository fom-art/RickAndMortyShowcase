package com.fomart.rms.shared.feature.character_details.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fomart.rms.core.designsystem.components.DefaultTopBar
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import rickandmortyshowcase.shared.feature.character_details.generated.resources.Res
import rickandmortyshowcase.shared.feature.character_details.generated.resources.add_to_favorites
import rickandmortyshowcase.shared.feature.character_details.generated.resources.no_character_details
import rickandmortyshowcase.shared.feature.character_details.generated.resources.remove_from_favorites

@Composable
fun CharacterDetailsRoute(
    modifier: Modifier = Modifier,
    viewModel: CharacterDetailsViewModel = koinInject(),
    characterId: String,
    navigateBack: () -> Unit,
) {
    viewModel.setCharacterId(characterId)
    val character = viewModel.character.collectAsState().value

    Scaffold(
        modifier = modifier,
        topBar = {
            DefaultTopBar(
                title = character?.name ?: "",
                navigateBack = navigateBack,
                trailingIcon = {
                    IconButton(onClick = viewModel::toggleFavorite) {
                        character?.let {
                            val description = stringResource(
                                if (character.favorite)
                                    Res.string.remove_from_favorites
                                else
                                    Res.string.add_to_favorites
                            )

                            val targetImageVector = if (character.favorite) Icons.Filled.Star else Icons.Outlined.Star

                            val targetTint = if (character.favorite)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.onBackground

                            val animatedTint by animateColorAsState(targetValue = targetTint, label = "IconTintAnimation")

                            Crossfade(targetState = targetImageVector, label = "IconCrossfade") { imageVector ->
                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    imageVector = imageVector,
                                    tint = animatedTint,
                                    contentDescription = description
                                )
                            }
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        character?.let {
            CharacterDetailsScreen(
                modifier = modifier.padding(innerPadding),
                character = character
            )
        }
    }
}