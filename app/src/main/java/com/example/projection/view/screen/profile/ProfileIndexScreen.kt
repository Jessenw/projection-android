package com.example.projection.view.screen.profile

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.navigation.NavHostController
import com.example.projection.view.component.standardlist.StandardListItem
import com.example.projection.view.component.standardlist.StandardListItemViewModel
import com.example.projection.view.component.standardlist.StandardListView

@Composable
fun ProfileIndexScreen(
    navController: NavHostController,
    viewModel: ProfileIndexViewModel = hiltViewModel()
) {
    StandardListView(navController, viewModel)
}
