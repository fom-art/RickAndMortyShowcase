import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fomart.rms.core.designsystem.components.CharacterPreviewItem
import com.fomart.rms.core.model.CharacterPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@Composable
fun CharactersPreviewsList(
    modifier: Modifier = Modifier,
    charactersPreviews: List<CharacterPreview>,
    searchMode: Boolean = false,
    selectedCharacter: CharacterPreview? = null,
    onSelectCharacter: (CharacterPreview) -> Unit,
    canLoadMode: Boolean = false,
    loadMore: () -> Unit = {},
) {
    val listState = rememberLazyListState()

    LaunchedEffect(listState, charactersPreviews.size) {
        snapshotFlow { listState.layoutInfo }
            .map { layoutInfo ->
                val totalItems = layoutInfo.totalItemsCount
                val lastVisibleIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                lastVisibleIndex >= (totalItems * 0.8).toInt()
            }
            .distinctUntilChanged()
            .collectLatest { isAtEightyPercent ->
                if (isAtEightyPercent && canLoadMode) {
                    loadMore()
                }
            }
    }


    LazyColumn(
        state = listState,
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
