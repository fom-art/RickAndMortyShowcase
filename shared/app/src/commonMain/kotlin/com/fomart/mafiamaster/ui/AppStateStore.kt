import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.fomart.mafiamaster.ui.TopLevelDestination
import com.fomart.rms.core.data.util.NetworkMonitor
import com.fomart.rms.shared.feature.catalog.navigation.navigateToCatalog
import com.fomart.rms.shared.feature.favorites.navigation.navigateToFavorites
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberApplicationState(
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): AppStateStore {
    return remember(networkMonitor, coroutineScope, navController) {
        AppStateStore(
            networkMonitor = networkMonitor,
            navController = navController,
            coroutineScope = coroutineScope
        )
    }
}

@Stable
class AppStateStore(
    val networkMonitor: NetworkMonitor,
    val navController: NavHostController,
    private val coroutineScope: CoroutineScope,
) {
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    var isOnline by mutableStateOf(false)
        private set

    var isLoading by mutableStateOf(false)
        private set

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() {
            return TopLevelDestination.entries.firstOrNull { topLevelDestination ->
                currentDestination?.hasRoute(route = topLevelDestination.screenRoute) ?: false
            }
        }
    val showNavigationBar
        @Composable get() = currentTopLevelDestination != null

    init {
        coroutineScope.launch {
            val isOnlineFlow = networkMonitor.checkConnection()
            isOnlineFlow.collect { isOnlineValue ->
                Napier.d { "isOnline $isOnlineValue" }
                isOnline = isOnlineValue
                isLoading = !isOnline
            }
        }
    }

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: $topLevelDestination") {
            val topLevelNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                val startDestination = navController.graph.findStartDestination().route!!
                popUpTo(startDestination) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }

            when (topLevelDestination) {
                is TopLevelDestination.Characters -> navController.navigateToCatalog(topLevelNavOptions)
                TopLevelDestination.Favorites -> navController.navigateToFavorites(topLevelNavOptions)
            }
        }
    }
}