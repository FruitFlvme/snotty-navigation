package com.fruitflvme.snotty_navigation

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fruitflvme.snotty_navigation.model.settings.Theme
import com.fruitflvme.snotty_navigation.ui.core.activity
import com.fruitflvme.snotty_navigation.ui.core.nav.NavDestination
import com.fruitflvme.snotty_navigation.ui.design.theme.SnottyTheme
import com.fruitflvme.snotty_navigation.ui.location.LocationDestination
import com.fruitflvme.snotty_navigation.ui.location.LocationHelpDestination
import com.fruitflvme.snotty_navigation.ui.location.LocationPermissionRequiredContent
import com.fruitflvme.snotty_navigation.ui.settings.SettingsDestination
import com.fruitflvme.snotty_navigation.ui.settings.SettingsViewModel
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@Composable
fun MainComponent(
    viewModel: SettingsViewModel = hiltViewModel(),
    navHostController: NavHostController,
    windowWidthSizeClass: WindowWidthSizeClass
) {
    val theme by viewModel.theme.collectAsStateWithLifecycle()
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val useDarkTheme by remember(theme) {
        derivedStateOf {
            when (theme) {
                Theme.DEVICE -> isSystemInDarkTheme
                Theme.DARK -> true
                Theme.LIGHT -> false
                null -> false
            }
        }
    }
    val systemBarStyle =
        SystemBarStyle.auto(Color.Transparent.toArgb(), Color.Transparent.toArgb()) { useDarkTheme }
    LocalContext.current.activity?.enableEdgeToEdge(systemBarStyle, systemBarStyle)

    SnottyTheme(useDarkTheme = useDarkTheme) {
        val context = LocalContext.current
        val locationPermissions = remember { listOf(ACCESS_FINE_LOCATION) }
        val locationPermissionsState = rememberMultiplePermissionsState(locationPermissions)
        val isCompactWidthWindow = windowWidthSizeClass == WindowWidthSizeClass.Compact
        val mainNavDestinations = setOf(LocationDestination, SettingsDestination)
        val snackbarHostState = remember { SnackbarHostState() }
        if (locationPermissionsState.allPermissionsGranted) {
            Scaffold(
                bottomBar = bottomBar@{
                    if (!isCompactWidthWindow) return@bottomBar
                    MainNavigationBar(navHostController, mainNavDestinations)
                },
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            ) { contentPadding ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (!isCompactWidthWindow) {
                        MainNavigationRail(
                            navHostController,
                            mainNavDestinations,
                            modifier = Modifier.fillMaxHeight()
                        )
                    }
                    NavHost(
                        navHostController,
                        startDestination = LocationDestination.route,
                        enterTransition = { EnterTransition.None },
                        exitTransition = { ExitTransition.None }
                    ) {
                        with(LocationDestination) {
                            composable(navHostController, snackbarHostState, contentPadding)
                        }
                        with(SettingsDestination) {
                            composable(navHostController, snackbarHostState, contentPadding)
                        }
                        with(LocationHelpDestination) {
                            composable(navHostController, snackbarHostState, contentPadding)
                        }
                    }
                }
            }
        } else {
            LocationPermissionRequiredContent(
                locationPermissionsState,
                onSettingsClick = { navigateToSettings(context) },
                Modifier.fillMaxSize()
            )
        }
    }

}

@Composable
private fun MainNavigationBar(
    navHostController: NavHostController,
    mainNavDestination: Set<NavDestination.MainNavDestination>,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier) {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        mainNavDestination.forEach { mainNavDestination ->
            NavigationBarItem(
                selected = currentRoute?.startsWith(mainNavDestination.route) ?: false,
                onClick = {
                    onNavigationItemClick(currentRoute, mainNavDestination, navHostController)
                },
                icon = { Icon(imageVector = mainNavDestination.navIcon, null) },
                label = { Text(stringResource(mainNavDestination.navLabelRes)) }
            )
        }
    }
}

@Composable
private fun MainNavigationRail(
    navHostController: NavHostController,
    mainNavDestination: Set<NavDestination.MainNavDestination>,
    modifier: Modifier = Modifier
) {
    NavigationRail(modifier) {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            mainNavDestination.forEach { mainNavDestination ->
                NavigationRailItem(
                    selected = currentRoute?.startsWith(mainNavDestination.route) ?: false,
                    onClick = {
                        onNavigationItemClick(currentRoute, mainNavDestination, navHostController)
                    },
                    icon = { Icon(imageVector = mainNavDestination.navIcon, null) },
                    label = { Text(stringResource(mainNavDestination.navLabelRes)) },
                )
            }
        }

        Spacer(Modifier.weight(1f))
    }
}

private fun navigateToSettings(context: Context) {
    val settingsIntent = Intent(
        android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", context.packageName, null)
    )
    ContextCompat.startActivity(context, settingsIntent, null)
}

private fun onNavigationItemClick(
    currentRoute: String?,
    mainNavDestination: NavDestination.MainNavDestination,
    navHostController: NavHostController
) {
    if (currentRoute == mainNavDestination.route || currentRoute == null) return
    navHostController.navigate(mainNavDestination.route) {
        launchSingleTop = true
        popUpTo(currentRoute) { inclusive = true }
        restoreState = true
    }
}