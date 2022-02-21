package com.example.projection.view.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
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

@Composable
fun RootScreen() {
    val navController = rememberNavController()

    // Routes available on bottom navigation
    val navItems = listOf(
        Route.ProjectIndex,
        Route.Profile
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("App") },
                navigationIcon = if (navController.previousBackStackEntry != null) {
                    {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                } else { null }
            )
        },
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                navItems.forEach { route ->
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
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
            composable(Route.Profile.route) { ProfileScreen(navController) }
        }
    }
}