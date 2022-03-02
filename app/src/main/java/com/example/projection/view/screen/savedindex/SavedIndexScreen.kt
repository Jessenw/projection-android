package com.example.projection.view.screen.savedindex

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.projection.view.component.ProjectList

@Composable
fun SavedIndexScreen(
    navController: NavHostController,
    viewModel: SavedIndexViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    ProjectList(navController, viewModel)
}