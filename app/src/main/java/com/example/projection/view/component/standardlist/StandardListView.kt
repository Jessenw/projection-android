package com.example.projection.view.component.standardlist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController

@Composable
fun StandardListView(
    navController: NavHostController,
    viewModel: StandardListViewModel
) {
    val cells = viewModel.dataSource.observeAsState()

    LazyColumn {
        cells.value?.let {
            items(it) { itemViewModel ->
                StandardListItem(navController, itemViewModel)
            }
        }
    }
}
