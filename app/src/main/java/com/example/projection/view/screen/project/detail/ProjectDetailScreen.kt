package com.example.projection.view.screen.project.detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.projection.view.screen.project.detail.viewmodel.GroupbuyDetailViewModelImpl

@Composable
fun ProjectShowScreen(
    navController: NavHostController,
    viewModel: GroupbuyDetailViewModelImpl = hiltViewModel()
) {
    val project = viewModel.dataSource.observeAsState()
    
    project.value?.data?.let { 
        Text(it.content)
    }
}
