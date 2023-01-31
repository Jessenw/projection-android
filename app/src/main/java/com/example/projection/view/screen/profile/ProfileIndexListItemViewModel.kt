package com.example.projection.view.screen.profile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.example.projection.view.components.standardlist.StandardListItemViewModel
import com.example.projection.view.components.standardlist.ToggleIconViewModel
import com.example.projection.view.navigation.Route

class ProfileIndexListItemViewModel(
    val navController: NavHostController,
    override val title: Int,
    override val startIcon: ImageVector?,
    override val endIcon: ImageVector? = Icons.Filled.ChevronRight,
    override val endToggleIcon: ToggleIconViewModel? = null
) : StandardListItemViewModel {

    override fun tapped() {
        navController.navigate(Route.ThemeIndex.route)
    }
}
