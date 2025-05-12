import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.fomart.mafiamaster.ui.TopLevelDestination
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.InternalSerializationApi

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
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() {
            val current = currentDestination
            val topLevel = TopLevelDestination.values.firstOrNull { topLevelDestination ->
                current?.hasRoute(route = topLevelDestination.screenRoute) ?: false
            }
            Napier.d { "Current: $current, TopLevel: $topLevel" }
            return topLevel
        }

    @OptIn(InternalSerializationApi::class)
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: $topLevelDestination") {
            val topLevelNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination()) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }

            val route = topLevelDestination.graphRoute

            navController.navigate(route, topLevelNavOptions)
        }
    }
}