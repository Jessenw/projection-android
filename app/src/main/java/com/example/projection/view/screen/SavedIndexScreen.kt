package com.example.projection.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.projection.view.component.ProjectList
import com.example.projection.view.screen.projectindex.SavedIndexViewModel

@Composable
fun SavedIndexScreen(
    navController: NavHostController,
    viewModel: SavedIndexViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    Column {
        ProjectList(navController, viewModel)
    }
}
