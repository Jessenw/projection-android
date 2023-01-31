package com.example.projection.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.projection.view.components.ProjectList
import com.example.projection.view.components.TopAppBarScreen
import com.example.projection.view.navigation.Route
import com.example.projection.view.screen.project.index.viewmodel.SavedIndexViewModel

@Composable
fun SavedIndexScreen(
    navController: NavHostController,
    viewModel: SavedIndexViewModel = hiltViewModel()
) {
    TopAppBarScreen(title = Route.SavedIndex.resourceId) {
        Column {
            ProjectList(navController, viewModel)
        }
    }
}
