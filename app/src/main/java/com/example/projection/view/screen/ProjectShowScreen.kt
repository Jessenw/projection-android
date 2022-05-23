package com.example.projection.view.screen

import android.app.Application
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Composable
fun ProjectShowScreen(
    navController: NavHostController,
    viewModel: GroupbuyDetailViewModelImpl = hiltViewModel()
) {
    Text("ShowScreen")
}

interface GroupbuyDetailViewModel {
    val dataSource: Result<ProjectDetail>
}

@HiltViewModel
class GroupbuyDetailViewModelImpl @Inject constructor(
    savedStateHandle: SavedStateHandle,
    application: Application,
) : AndroidViewModel(application), GroupbuyDetailViewModel {

    override val dataSource: Result<ProjectDetail> = Result.success(ProjectDetail("1001", "Content"))

    init {
        println("SavedStateHandle > Keys ${savedStateHandle.keys()}")
        println("SavedStateHandle > project_id ${savedStateHandle.get<String>("project_id") }")
    }
}

data class ProjectDetail(
    val id: String,
    val content: String
)
