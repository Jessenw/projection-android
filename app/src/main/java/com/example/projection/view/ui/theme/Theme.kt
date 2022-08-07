package com.example.projection.view.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ProjectionTheme(
    viewModel: ThemeViewModelImpl = hiltViewModel(),
    content: @Composable () -> Unit
) {
    val theme = viewModel.theme.observeAsState()
    val systemUi = rememberSystemUiController()
    val colors = resolveTheme(theme.value)

    systemUi.setSystemBarsColor(colors.background)

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

/**
 * Given a theme ID, return the associated [Colors][androidx.compose.material.Colors]
 */
fun resolveTheme(themeId: Int?) =
    when(themeId) {
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
