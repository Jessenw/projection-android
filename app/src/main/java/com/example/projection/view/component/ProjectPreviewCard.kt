package com.example.projection.view.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.projection.data.model.ProjectPreview
import com.example.projection.view.navigation.Route

@Composable
fun ProjectPreviewCardText(
    text: String,
    style: TextStyle
) {
    Text(
        text = text,
        style = style,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

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
        var saved by remember { mutableStateOf(preview.saved) }

        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Weighting the text column allows the icon to have layout priority
            Column(modifier = Modifier.weight(1f)) {
                ProjectPreviewCardText(
                    preview.title,
                    MaterialTheme.typography.subtitle2
                )
                Spacer(modifier = Modifier.height(4.dp))
                ProjectPreviewCardText(
                    preview.author,
                    MaterialTheme.typography.body2
                )
            }
            // Save button
            IconToggleButton(
                checked = saved,
                onCheckedChange = { saved = it },
                modifier = Modifier.requiredWidth(IntrinsicSize.Min)
            ) {
                val tint by animateColorAsState(
                    if (saved) Color.Red
                    else Color.LightGray
                )
                Icon(
                    if (saved) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Save ${preview.title}",
                    tint = tint,
                )
            }
        }
    }
}
