package com.example.projection.view.ui.theme

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

@Composable
fun ProjectionTheme(
    themeService: ThemeService,
    content: @Composable () -> Unit) {

    var scheme = themeService.currentTheme.observeAsState(BotanicalColorPalette)

    MaterialTheme(
        colors = scheme.value,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

sealed class Palette(
    @StringRes val nameId: Int,
    val color: Colors,
) {
    object OliviaColorScheme: Palette(R.string.olivia_palette, OliviaColorPalette)
    object BotanicalColorScheme: Palette(R.string.botanical_palette, BotanicalColorPalette)

}

interface ThemeService {
    val currentTheme: LiveData<Colors>

    fun updateTheme(palette: Palette)
}

class ThemeServiceImpl : ThemeService {

    private val _currentTheme = MutableLiveData<Colors>()
    override var currentTheme: LiveData<Colors> = _currentTheme

    override fun updateTheme(palette: Palette) {
        _currentTheme.value = palette.color
    }
}
