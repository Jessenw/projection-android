package com.example.projection.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.projection.view.component.ProjectPreviewCard
import com.example.projection.view.component.viewmodel.ListViewModel

@Composable
fun ProjectList(
    navController: NavHostController,
    viewModel: ListViewModel
) {
    val projects = viewModel.dataSource.observeAsState()

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        projects.value?.let {
            items(it) { project ->
                ProjectPreviewCard(navController, project)
            }
        }
    }
}
