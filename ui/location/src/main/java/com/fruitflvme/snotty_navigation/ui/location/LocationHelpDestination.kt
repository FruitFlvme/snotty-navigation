package com.fruitflvme.snotty_navigation.ui.location

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fruitflvme.snotty_navigation.ui.core.nav.NavDestination
import com.fruitflvme.snotty_navigation.ui.core.nav.defaultEnterTransition
import com.fruitflvme.snotty_navigation.ui.core.nav.defaultExitTransition
import com.fruitflvme.snotty_navigation.ui.core.nav.defaultPopEnterTransition
import com.fruitflvme.snotty_navigation.ui.core.nav.defaultPopExitTransition

object LocationHelpDestination : NavDestination {

    override val route = "location/help"

    context(NavGraphBuilder)
    override fun composable(
        navController: NavController,
        snackBarHostState: SnackbarHostState,
        contentPadding: PaddingValues
    ) {
        composable(
            route,
            enterTransition = defaultEnterTransition(),
            exitTransition = defaultExitTransition(),
            popEnterTransition = defaultPopEnterTransition(),
            popExitTransition = defaultPopExitTransition()
        ) {
            LocationHelpComponent(contentPadding, onUpClick = { navController.popBackStack() })
        }
    }
}