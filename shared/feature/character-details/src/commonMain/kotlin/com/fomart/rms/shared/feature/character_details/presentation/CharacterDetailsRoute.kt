package com.fomart.rms.shared.feature.character_details.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.resources.stringResource
import rickandmortyshowcase.shared.feature.character_details.generated.resources.Res
import rickandmortyshowcase.shared.feature.character_details.generated.resources.no_character_details

@Composable
fun CharacterDetailsRoute(
    modifier: Modifier = Modifier,
    viewModel: CharacterDetailsViewModel
) {
    viewModel.character?.let { character ->
        CharacterDetailsScreen(
            modifier = modifier,
            character = character
        )
    }

    if (viewModel.character == null) {
        Text(
            modifier = Modifier.fillMaxSize(),
            text = stringResource(Res.string.no_character_details),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}