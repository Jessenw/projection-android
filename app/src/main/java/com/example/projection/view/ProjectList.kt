package com.example.projection.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import com.example.projection.MainActivityViewModel

@Composable
fun ProjectList(
    viewModel: MainActivityViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val projects = viewModel.dataSource.observeAsState()

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        projects.value?.let {
            items(it) { project ->
                ProjectCard(project)
            }
        }
    }
}

//@Preview
//@Composable
//fun PreviewProjectList() {
//    ProjectList(ProjectPreviewSampleData.projectPreviewSample)
//}