package com.example.projection.view.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.projection.view.navigation.Route
import com.example.projection.view.screen.projectindex.ProjectIndexScreen

@Composable
fun RootScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // Routes available on bottom navigation
    val bottomNavItems = listOf(
        Route.ProjectIndex,
        Route.SavedIndex,
        Route.Profile
    )

    Scaffold(
        bottomBar = {
            BottomNavigation {
                bottomNavItems.forEach { route ->
                    BottomNavigationItem(
                        icon = { route.icon?.let { Icon(it, contentDescription = null) } },
                        label = { Text(stringResource(route.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == route.route } == true,
                        onClick = {
                            navController.navigate(route.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }

                                // Avoid appending same destination when reselecting
                                // the same item
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
    }) { innerPadding ->
        NavHost(
            navController,
            startDestination = Route.ProjectIndex.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Route.ProjectIndex.route) { ProjectIndexScreen(navController) }
            composable(Route.ProjectShow.route) { ProjectShowScreen(navController) }
            composable(Route.SavedIndex.route) { SavedIndexScreen(navController) }
            composable(Route.Profile.route) { ProfileScreen(navController) }
        }
    }
}