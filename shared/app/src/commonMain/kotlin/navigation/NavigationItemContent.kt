package navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.fomart.rms.characters.ui.viewmodel.CharactersListType

data class NavigationItemContent(
    val charactersListType: CharactersListType,
    val icon: ImageVector,
    val text: String
)