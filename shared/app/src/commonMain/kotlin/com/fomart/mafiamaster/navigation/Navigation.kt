package com.fomart.mafiamaster.navigation

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RmsNavigationSuiteScaffold(
    modifier: Modifier = Modifier,
    navigationSuiteItems: RmsNavigationSuiteScope.() -> Unit,
    showNavigationBar: Boolean,
    content: @Composable () -> Unit,
) {
    BoxWithConstraints(modifier = modifier) {
        val layoutType = if (!showNavigationBar) {
            NavigationSuiteType.None
        } else {
            val widthDp = maxWidth
            when {
                widthDp < 600.dp -> NavigationSuiteType.NavigationBar
                widthDp < 840.dp -> NavigationSuiteType.NavigationRail
                else -> NavigationSuiteType.NavigationDrawer
            }
        }

        val navigationSuiteItemColors = NavigationSuiteItemColors(
            navigationBarItemColors = NavigationBarItemDefaults.colors(
                selectedIconColor = RmsNavigationDefaults.navigationSelectedItemColor(),
                unselectedIconColor = RmsNavigationDefaults.navigationContentColor(),
                selectedTextColor = RmsNavigationDefaults.navigationSelectedItemColor(),
                unselectedTextColor = RmsNavigationDefaults.navigationContentColor(),
                indicatorColor = RmsNavigationDefaults.navigationIndicatorColor(),
            ),
            navigationRailItemColors = NavigationRailItemDefaults.colors(
                selectedIconColor = RmsNavigationDefaults.navigationSelectedItemColor(),
                unselectedIconColor = RmsNavigationDefaults.navigationContentColor(),
                selectedTextColor = RmsNavigationDefaults.navigationSelectedItemColor(),
                unselectedTextColor = RmsNavigationDefaults.navigationContentColor(),
                indicatorColor = RmsNavigationDefaults.navigationIndicatorColor(),
            ),
            navigationDrawerItemColors = NavigationDrawerItemDefaults.colors(
                selectedIconColor = RmsNavigationDefaults.navigationSelectedItemColor(),
                unselectedIconColor = RmsNavigationDefaults.navigationContentColor(),
                selectedTextColor = RmsNavigationDefaults.navigationSelectedItemColor(),
                unselectedTextColor = RmsNavigationDefaults.navigationContentColor(),
            ),
        )

        NavigationSuiteScaffold(
            navigationSuiteItems = {
                RmsNavigationSuiteScope(
                    navigationSuiteScope = this,
                    navigationSuiteItemColors = navigationSuiteItemColors,
                ).run(navigationSuiteItems)
            },
            layoutType = layoutType,
            containerColor = Color.Transparent,
            navigationSuiteColors = NavigationSuiteDefaults.colors(
                navigationBarContentColor = RmsNavigationDefaults.navigationContentColor(),
                navigationRailContainerColor = Color.Transparent,
            ),
        ) {
            content()
        }
    }
}


class RmsNavigationSuiteScope internal constructor(
    private val navigationSuiteScope: NavigationSuiteScope,
    private val navigationSuiteItemColors: NavigationSuiteItemColors,
) {
    fun item(
        modifier: Modifier = Modifier,
        selected: Boolean,
        onClick: () -> Unit,
        icon: @Composable () -> Unit,
        selectedIcon: @Composable () -> Unit = icon,
        label: @Composable (() -> Unit)? = null,
    ) = navigationSuiteScope.item(
        selected = selected,
        onClick = onClick,
        icon = {
            if (selected) {
                selectedIcon()
            } else {
                icon()
            }
        },
        label = label,
        colors = navigationSuiteItemColors,
        modifier = modifier,
    )
}

object RmsNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}