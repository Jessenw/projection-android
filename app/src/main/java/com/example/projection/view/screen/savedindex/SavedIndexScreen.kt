package com.example.projection.view.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.projection.view.component.ProjectList
import com.example.projection.view.screen.savedindex.SavedIndexViewModel

@Composable
fun SavedIndexScreen(
    navController: NavHostController,
    viewModel: SavedIndexViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    ProjectList(navController, viewModel)
}