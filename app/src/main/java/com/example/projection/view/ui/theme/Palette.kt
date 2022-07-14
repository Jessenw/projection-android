package com.example.projection.view.ui.theme

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import com.example.projection.R

@SuppressLint("ConflictingOnColor")
val BotanicalColorPalette = lightColors(
    primary = BotanicalPrimary,
    primaryVariant = BotanicalPrimary,
    secondary = BotanicalPrimary,
    background = BotanicalBackground,
    surface = BotanicalSurface,
    onPrimary = BotanicalBackground,
    onSurface = BotanicalOnSurface
)

val EightOhOhEightPalette = darkColors(
    primary = EightOhOhEightPrimary,
    primaryVariant = EightOhOhEightPrimary,
    secondary = EightOhOhEightPrimary,
    background = EightOhOhEightBackground,
    surface = EightOhOhEightSurface,
    onSurface = EightOhOhEightOnSurface
)

@SuppressLint("ConflictingOnColor")
val OliviaColorPalette = darkColors(
    primary = OliviaPrimary,
    primaryVariant = OliviaPrimary,
    secondary = OliviaPrimary,
    background = OliviaBackground,
    surface = OliviaSurface,
    onPrimary = OliviaBackground,
    onSurface = Color.White
)

sealed class Palette(
    @StringRes val nameId: Int,
    val color: Colors,
) {
    object EightOhOhEight: Palette(R.string.eight_oh_oh_eight_palette, EightOhOhEightPalette)
    object Botanical: Palette(R.string.botanical_palette, BotanicalColorPalette)
    object Olivia: Palette(R.string.olivia_palette, OliviaColorPalette)
}
