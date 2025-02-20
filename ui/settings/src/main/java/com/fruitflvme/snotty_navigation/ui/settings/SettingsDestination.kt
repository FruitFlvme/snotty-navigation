package com.fruitflvme.snotty_navigation.ui.settings

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fruitflvme.snotty_navigation.ui.core.nav.NavDestination
import com.fruitflvme.snotty_navigation.ui.core.nav.bottomNavEnterTransition
import com.fruitflvme.snotty_navigation.ui.core.nav.bottomNavExitTransition
import com.fruitflvme.snotty_navigation.ui.core.nav.bottomNavPopEnterTransition
import com.fruitflvme.snotty_navigation.ui.core.nav.bottomNavPopExitTransition

data object SettingsDestination : NavDestination.MainNavDestination {

    override val route = "settings"
    override val navIcon = Icons.Rounded.Settings
    override val navLabelRes = R.string.ui_settings_title

    context(NavGraphBuilder)
    override fun composable(
        navController: NavController,
        snackbarHostState: SnackbarHostState,
        contentPadding: PaddingValues
    ) {
        composable(
            route,
            enterTransition = bottomNavEnterTransition(),
            exitTransition = bottomNavExitTransition(),
            popEnterTransition = bottomNavPopEnterTransition(),
            popExitTransition = bottomNavPopExitTransition()
        ) {
            val context = LocalContext.current
            val viewModel: SettingsViewModel = hiltViewModel()
//                val compassMode by viewModel.compassMode.collectAsStateWithLifecycle()
//                val compassNorthVibration by viewModel.compassNorthVibration
//                    .collectAsStateWithLifecycle()
            val theme by viewModel.theme.collectAsStateWithLifecycle()
            val units by viewModel.units.collectAsStateWithLifecycle()
            val coordinatesFormat by viewModel.coordinatesFormat.collectAsStateWithLifecycle()
            val locationAccuracyVisibility by viewModel.locationAccuracyVisibility
                .collectAsStateWithLifecycle()
            SettingsComponent(
                theme = theme,
                onThemeChange = viewModel::onThemeChange,
                units = units,
                onUnitsChange = viewModel::onUnitsChange,
                coordinatesFormat = coordinatesFormat,
                onCoordinatesFormatChange = viewModel::onCoordinatesFormatChange,
                locationAccuracyVisibility = locationAccuracyVisibility,
                onLocationAccuracyVisibilityChange = viewModel::onLocationAccuracyVisibilityChange,
                contentPadding = contentPadding,
            )
        }
    }
}
