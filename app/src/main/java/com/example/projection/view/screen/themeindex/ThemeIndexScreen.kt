package com.example.projection.view.screen.themeindex

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.projection.view.components.TopAppBarScreen
import com.example.projection.view.components.standardlist.StandardListView
import com.example.projection.view.navigation.Route

@Composable
fun ThemeIndexScreen(
    navController: NavHostController,
    viewModel: ThemeIndexViewModel = hiltViewModel()
) {
    TopAppBarScreen(title = Route.ThemeIndex.resourceId) {
        StandardListView(navController, viewModel)
    }
}
