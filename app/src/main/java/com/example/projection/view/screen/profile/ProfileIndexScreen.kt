package com.example.projection.view.screen.profile

import android.app.Activity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.projection.di.ViewModelFactoryModule
import com.example.projection.view.component.standardlist.StandardListItem
import com.example.projection.view.component.standardlist.StandardListItemViewModel
import com.example.projection.view.component.standardlist.StandardListView
import dagger.hilt.android.EntryPointAccessors

@Composable
fun ProfileIndexScreen(
    navController: NavHostController,
    viewModel: ProfileIndexViewModel = profileIndexViewModel(navController)
) {
    StandardListView(navController, viewModel)
}

@Composable
fun profileIndexViewModel(navController: NavHostController): ProfileIndexViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity, 
        ViewModelFactoryModule::class.java).profileIndexViewModelFactory()
    return viewModel(factory = ProfileIndexViewModel.provideFactory(factory, navController))
}
