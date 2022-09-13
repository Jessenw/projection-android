package com.example.projection.view.component.standardlist

import androidx.compose.ui.graphics.vector.ImageVector

interface StandardListItemViewModel {
    val title: Int
    val startIcon: ImageVector?
    val endIcon: ImageVector?
    val endToggleIcon: ToggleIconViewModel?

    fun tapped()
}

class ToggleIconViewModel(
    val iconOff: ImageVector,
    val iconOn: ImageVector,
    var saved: Boolean = false
)
