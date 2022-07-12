package com.example.projection.view.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@SuppressLint("ConflictingOnColor")
private val BotanicalColorPalette = lightColors(
    primary = BotanicalPrimary,
    primaryVariant = BotanicalPrimary,
    secondary = BotanicalPrimary,
    background = BotanicalBackground,
    surface = BotanicalSurface,
    onPrimary = BotanicalBackground,
    onSurface = BotanicalOnSurface
)

@SuppressLint("ConflictingOnColor")
private val OliviaColorPalette = lightColors(
    primary = OliviaPrimary,
    primaryVariant = OliviaPrimary,
    secondary = OliviaPrimary,
    background = OliviaBackground,
    surface = OliviaSurface,
    onPrimary = OliviaBackground,
    onSurface = Color.White
)

@Composable
fun ProjectionTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = BotanicalColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
