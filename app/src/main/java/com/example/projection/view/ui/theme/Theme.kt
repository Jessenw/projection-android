package com.example.projection.view.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProjectionTheme(
    viewModel: ThemeViewModelImpl = hiltViewModel(),
    content: @Composable () -> Unit
) {
    val theme = viewModel.theme.observeAsState()
    val colors: Colors =
        when (theme.value) {
            Palette.Botanical.nameId -> {
                Palette.Botanical.color
            }
            Palette.EightOhOhEight.nameId -> {
                Palette.EightOhOhEight.color
            }
            Palette.Olivia.nameId -> {
                Palette.Olivia.color
            }
            else -> {
                Palette.EightOhOhEight.color
            }
        }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
