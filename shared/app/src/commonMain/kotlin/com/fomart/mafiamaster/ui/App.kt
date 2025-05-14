package com.fomart.mafiamaster.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult.ActionPerformed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import com.fomart.mafiamaster.navigation.RmsNavHost
import com.fomart.mafiamaster.navigation.RmsNavigationSuiteScaffold
import com.fomart.rms.core.data.util.NetworkMonitor
import com.fomart.rms.core.designsystem.components.LoadingOverlay
import io.github.aakira.napier.Napier
import org.koin.compose.koinInject
import rememberApplicationState

@Composable
fun App(
    modifier: Modifier = Modifier,
    networkMonitor: NetworkMonitor = koinInject(),
) {
    val appState = rememberApplicationState(
        networkMonitor = networkMonitor
    )
    val currentDestination = appState.currentTopLevelDestination
    val snackbarHostState = remember { SnackbarHostState() }

    Napier.d { "Current destination: $currentDestination" }

    RmsNavigationSuiteScaffold(
        modifier = modifier,
        navigationSuiteItems = {
            appState.topLevelDestinations.forEach { destination ->
                val selected = currentDestination == destination
                item(
                    selected = selected,
                    onClick = {
                        Napier.d { "navigation with destination: $destination" }
                        appState.navigateToTopLevelDestination(destination)
                    },
                    icon = {
                        Icon(
                            imageVector = destination.unselectedIcon.asImageVector(),
                            contentDescription = null,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = destination.selectedIcon.asImageVector(),
                            contentDescription = null,
                        )
                    },
                    label = {
                        Text(
                            text = destination.iconUiText.asString(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    modifier =
                        Modifier
                            .testTag("RmsNavItem")
                )
            }
        },
        showNavigationBar = appState.showNavigationBar,
    ) {
        RmsNavHost(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background),
            appState = appState,
            onShowSnackbar = { message, action ->
                snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = action,
                    duration = SnackbarDuration.Short,
                ) == ActionPerformed
            }
        )

        if (appState.isLoading) {
            LoadingOverlay()
        }
    }
}