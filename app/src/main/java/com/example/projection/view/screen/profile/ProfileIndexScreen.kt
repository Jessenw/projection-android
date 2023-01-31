package com.example.projection.view.screen.profile

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.projection.di.ViewModelFactoryModule
import com.example.projection.view.components.standardlist.StandardListView
import com.example.projection.view.navigation.Route
import com.example.projection.view.screen.project.index.TopAppBarScreen
import dagger.hilt.android.EntryPointAccessors

@Composable
fun ProfileIndexScreen(
    navController: NavHostController,
    viewModel: ProfileIndexViewModel = profileIndexViewModel(navController)
) {
    TopAppBarScreen(title = Route.Profile.resourceId) {
        StandardListView(navController, viewModel)
    }
}

@Composable
fun profileIndexViewModel(navController: NavHostController): ProfileIndexViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity, 
        ViewModelFactoryModule::class.java).profileIndexViewModelFactory()
    return viewModel(factory = ProfileIndexViewModel.provideFactory(factory, navController))
}
