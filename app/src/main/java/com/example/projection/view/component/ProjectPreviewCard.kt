package com.example.projection.view.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.projection.data.model.ProjectPreview
import com.example.projection.data.model.ProjectPreviewSampleData
import com.example.projection.view.navigation.Route

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProjectPreviewCard(
    navController: NavHostController,
    preview: ProjectPreview
) {
    Card(
        elevation = 12.dp,
        modifier = Modifier.fillMaxWidth(),
        onClick = { navController.navigate(Route.ProjectShow.route) }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
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