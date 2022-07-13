package com.example.projection.view.component.standardlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.projection.view.navigation.Route

@Composable
fun StandardListItem(
    navController: NavHostController,
    itemViewModel: StandardListItemViewModel
) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate(Route.ThemeIndex.route) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Filled.Brush,
            contentDescription = null,
            tint = MaterialTheme.colors.primary,
            modifier = Modifier.padding(start = 8.dp, end = 16.dp)
        )
        Text(
            text = itemViewModel.title,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            Icons.Filled.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colors.primary,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}
