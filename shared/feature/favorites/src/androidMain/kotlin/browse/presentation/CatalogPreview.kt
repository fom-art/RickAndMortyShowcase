package browse.presentation

import CharactersPreviewsList
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fomart.rms.core.designsystem.theme.RickyAndMortyShowcaseTheme
import com.fomart.rms.core.model.CharacterPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview
@Composable
fun CatalogPreview() {
    var charactersPreviews by remember { mutableStateOf(generateCharacterPreviews(16)) }
    var selectedCharacter by remember { mutableStateOf<CharacterPreview?>(null) }
    val scope = rememberCoroutineScope()

    RickyAndMortyShowcaseTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            Text("overall elements: ${charactersPreviews.size}")
            CharactersPreviewsList(
                charactersPreviews = charactersPreviews,
                searchMode = false,
                selectedCharacter = selectedCharacter,
                onSelectCharacter = { character -> selectedCharacter = character },
                canLoadMode = true,
                loadMore = {
                    scope.launch {
                        delay(500) // simulate network delay
                        charactersPreviews = charactersPreviews + generateCharacterPreviews(
                            count = 16,
                            startIndex = charactersPreviews.size
                        )
                    }
                }
            )
        }
    }
}

// Base preview character
val previewCharacter = CharacterPreview(
    id = "1",
    name = "Character",
    status = "The diva is never down",
    favorite = true,
    imageUrl = "https://upload.wikimedia.org/wikipedia/en/3/36/Rick_and_Morty_characters.jpg"
)

// Generate list of CharacterPreviews with unique IDs
fun generateCharacterPreviews(count: Int, startIndex: Int = 0): List<CharacterPreview> {
    return List(count) { index ->
        previewCharacter.copy(id = "${startIndex + index}")
    }
}
