package com.example.projection.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projection.MainActivity
import com.example.projection.model.ProjectPreview

@Composable
fun ProjectList(projects: List<ProjectPreview>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(projects) { project ->
            ProjectCard(project)
        }
    }
}

@Preview
@Composable
fun PreviewProjectList() {
    ProjectList(MainActivity.SampleData.projectPreviewSample)
}