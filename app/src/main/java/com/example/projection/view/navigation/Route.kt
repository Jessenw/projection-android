package com.example.projection.view.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.projection.R

sealed class Route(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector? = null
) {
    object Profile : Route("profile", R.string.profile, Icons.Filled.Person)
    object ProjectIndex : Route("project_index", R.string.project_index, Icons.Filled.Home)
    object ProjectShow : Route("project_show", R.string.project_show)
    object SavedIndex : Route("saved_index", R.string.saved_index, Icons.Filled.FavoriteBorder)
    object ThemeIndex : Route("theme_index", R.string.theme_index)
}
