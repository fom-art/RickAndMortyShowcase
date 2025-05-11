package com.fomart.rms.shared.feature.character_details.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.fomart.rms.core.model.Character
import org.jetbrains.compose.resources.stringResource
import rickandmortyshowcase.shared.feature.character_details.generated.resources.Res
import rickandmortyshowcase.shared.feature.character_details.generated.resources.gender
import rickandmortyshowcase.shared.feature.character_details.generated.resources.location
import rickandmortyshowcase.shared.feature.character_details.generated.resources.name
import rickandmortyshowcase.shared.feature.character_details.generated.resources.nothing_to_display
import rickandmortyshowcase.shared.feature.character_details.generated.resources.origin
import rickandmortyshowcase.shared.feature.character_details.generated.resources.species
import rickandmortyshowcase.shared.feature.character_details.generated.resources.status
import rickandmortyshowcase.shared.feature.character_details.generated.resources.type

@Composable
fun CharacterDetailsScreen(
    modifier: Modifier = Modifier,
    character: Character,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column {
                Row(modifier = Modifier) {
                    AsyncImage(
                        model = character.imageUrl,
                        contentDescription = "",
                        modifier = Modifier
                            .size(32.dp)
                            .clip(MaterialTheme.shapes.medium)
                    )
                    Column(modifier = Modifier.padding(start = 16.dp)) {
                        Text(
                            text = stringResource(Res.string.name),
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                        Text(
                            text = character.name,
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                CharacterDetailsTraitElement(
                    labelText = stringResource(Res.string.status),
                    text = character.status
                )
                CharacterDetailsTraitElement(
                    labelText = stringResource(Res.string.species),
                    text = character.species
                )
                CharacterDetailsTraitElement(
                    labelText = stringResource(Res.string.type),
                    text = character.type
                )
                CharacterDetailsTraitElement(
                    labelText = stringResource(Res.string.gender),
                    text = character.gender
                )
                CharacterDetailsTraitElement(
                    labelText = stringResource(Res.string.origin),
                    text = character.origin
                )
                CharacterDetailsTraitElement(
                    labelText = stringResource(Res.string.location),
                    text = character.location
                )
            }
        }
    }
}

@Composable
fun CharacterDetailsTraitElement(
    labelText: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(vertical = 8.dp)) {
        Text(
            text = labelText,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondary
        )
        Text(
            text = text,
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun CharacterDetailsEmptyLayout(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(Res.string.nothing_to_display),
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onTertiary,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}