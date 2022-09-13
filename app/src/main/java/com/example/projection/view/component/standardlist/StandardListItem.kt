package com.example.projection.view.component.standardlist

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { itemViewModel.tapped() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        itemViewModel.startIcon?.let {
            Icon(
                it,
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.padding(end = 16.dp)
            )
        }
        Text(
            text = stringResource(itemViewModel.title),
            style = MaterialTheme.typography.subtitle1,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.weight(1f))
        itemViewModel.endIcon?.let {
            Icon(
                it,
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
            )
        }
        itemViewModel.endToggleIcon?.let {
            var saved by remember { mutableStateOf(it.saved) }
            IconToggleButton(
                checked = saved,
                onCheckedChange = { ssaved ->
                    it.saved = ssaved
                },
                modifier = Modifier.requiredWidth(IntrinsicSize.Min)
            ) {
                val tint by animateColorAsState(
                    if (saved) MaterialTheme.colors.primary
                    else MaterialTheme.colors.primaryVariant
                )
                Icon(
                    if (saved) it.iconOn else it.iconOff,
                    contentDescription = "Save", // TODO: (Jesse) Update content description
                    tint = tint,
                )
            }
        }
    }
}
