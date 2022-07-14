package com.example.projection.view.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun ProjectionTheme(
    themeService: ThemeService,
    content: @Composable () -> Unit
) {
    val scheme = themeService.currentTheme.observeAsState(BotanicalColorPalette)

    MaterialTheme(
        colors = scheme.value,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
