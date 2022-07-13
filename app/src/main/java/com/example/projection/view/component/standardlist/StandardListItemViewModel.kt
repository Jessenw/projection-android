package com.example.projection.view.component.standardlist

import androidx.compose.ui.graphics.vector.ImageVector

interface StandardListItemViewModel {
    val title: String
    val startIcon: ImageVector?
    val endIcon: ImageVector?

    fun tapped()
}

class SimpleStandardListItemViewModel(
    override val title: String,
    override val startIcon: ImageVector? = null,
    override val endIcon: ImageVector? = null
): StandardListItemViewModel {
    override fun tapped() {}
}
