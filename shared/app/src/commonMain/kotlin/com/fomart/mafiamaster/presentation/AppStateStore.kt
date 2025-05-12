import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberApplicationState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): AppStateStore {
    return remember(coroutineScope, navController) {
        AppStateStore(
            navController = navController,
            coroutineScope = coroutineScope
        )
    }
}

@Stable
class AppStateStore(
    val navController: NavHostController,
    private val coroutineScope: CoroutineScope,
) {

}