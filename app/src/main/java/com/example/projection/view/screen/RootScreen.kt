package com.example.projection.view.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projection.view.navigation.Route
import com.example.projection.view.screen.profile.ProfileIndexScreen
import com.example.projection.view.screen.project.detail.ProjectShowScreen
import com.example.projection.view.screen.project.index.ProjectIndexScreen
import com.example.projection.view.screen.themeindex.ThemeIndexScreen

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
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.primary
            ) {
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
            composable(
                "${Route.ProjectShow.route}/{project_id}",
                listOf(navArgument("project_id") { NavType.StringType })) {
                ProjectShowScreen()
            }
            composable(Route.SavedIndex.route) { SavedIndexScreen(navController) }
            composable(Route.Profile.route) { ProfileIndexScreen(navController) }
            composable(Route.ThemeIndex.route) { ThemeIndexScreen(navController) }
        }
    }
}
