package com.fomart.rms.core.designsystem.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.fomart.rms.core.model.CharacterPreview
import org.jetbrains.compose.resources.stringResource
import rickandmortyshowcase.shared.core.designsystem.generated.resources.Res
import rickandmortyshowcase.shared.core.designsystem.generated.resources.favorite


@Composable
fun CharacterPreviewItem(
    character: CharacterPreview,
    selected: Boolean,
    searchMode: Boolean,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selected && searchMode)
                Color.Transparent
            else if (selected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.tertiaryContainer
        ),
        onClick = onCardClick
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val isInPreview = LocalInspectionMode.current
            if (isInPreview) {
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(44.dp)
                        .clip(MaterialTheme.shapes.small)
                        .background(Color.Black)
                )
            } else {
                AsyncImage(
                    model = character.imageUrl,
                    contentDescription = character.name,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(44.dp)
                        .clip(MaterialTheme.shapes.small)
                )
            }

            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = character.name,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    AnimatedVisibility(visible = character.favorite) {
                        Icon(
                            modifier = modifier
                                .size(14.dp)
                                .padding(start = 2.dp),
                            imageVector = Icons.Filled.Star,
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = stringResource(Res.string.favorite),
                        )
                    }
                }
                Text(
                    text = character.status,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}