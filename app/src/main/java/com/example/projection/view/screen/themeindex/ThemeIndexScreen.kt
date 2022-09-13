package com.example.projection.view.screen.themeindex

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.projection.view.component.standardlist.StandardListView

@Composable
fun ThemeIndexScreen(
    navController: NavHostController,
    viewModel: ThemeIndexViewModel = hiltViewModel()
) {
    StandardListView(navController, viewModel)
}
