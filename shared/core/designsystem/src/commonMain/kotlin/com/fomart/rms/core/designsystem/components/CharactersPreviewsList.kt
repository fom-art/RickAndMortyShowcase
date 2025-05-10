package com.fomart.rms.core.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fomart.rms.core.model.CharacterPreview

@Composable
fun CharactersPreviewsList(
    modifier: Modifier = Modifier,
    charactersPreviews: List<CharacterPreview>,
    searchMode: Boolean = false,
    selectedCharacter: CharacterPreview,
    onSelectCharacter: (CharacterPreview) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(charactersPreviews) { character ->
            CharacterPreviewItem(
                character = character,
                selected = selectedCharacter == character,
                searchMode = searchMode,
                onCardClick = { onSelectCharacter(character) },
            )
        }
    }
}


