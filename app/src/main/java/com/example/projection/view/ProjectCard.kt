package com.example.projection.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.projection.MainActivity
import com.example.projection.data.ProjectPreview
import com.example.projection.data.ProjectPreviewSampleData

@Composable
fun ProjectCard(preview: ProjectPreview) {
    Card(
        elevation = 12.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = preview.title,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = preview.author,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Preview
@Composable
fun PreviewProjectCard() {
    ProjectCard(ProjectPreviewSampleData.projectPreviewSample.first())
}

class SampleViewModel() : ViewModel() {

    var inputState by mutableStateOf("")
}

@Composable
fun SampleComposable(viewModel: SampleViewModel) {

    val inputState = viewModel.inputState

    Text(text = inputState)
}