package com.example.projection.view.navigation

import androidx.annotation.StringRes
import com.example.projection.R

sealed class Route(val route: String, @StringRes val resourceId: Int) {
    object ProjectIndex : Route("project_index", R.string.project_index)
    object ProjectShow : Route("project_show", R.string.project_show)
    object Profile : Route("profile", R.string.profile)
}