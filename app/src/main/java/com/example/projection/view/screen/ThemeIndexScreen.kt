package com.example.projection.view.screen

import android.app.Application
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.example.projection.view.component.standardlist.SimpleStandardListItemViewModel
import com.example.projection.view.component.standardlist.StandardListItemViewModel
import com.example.projection.view.component.standardlist.StandardListView
import com.example.projection.view.component.standardlist.StandardListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Composable
fun ThemeIndexScreen(
    navController: NavHostController,
    viewModel: ThemeIndexViewModel = hiltViewModel()
) {
    StandardListView(navController, viewModel)
}

@HiltViewModel
class ThemeIndexViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application), StandardListViewModel {

    private val _dataSource = MutableLiveData<List<StandardListItemViewModel>>()
    override val dataSource: LiveData<List<StandardListItemViewModel>> = _dataSource

    init {
        _dataSource.value = listOf(
            SimpleStandardListItemViewModel("Olivia"),
            SimpleStandardListItemViewModel("Botanical")
        )
    }
}


