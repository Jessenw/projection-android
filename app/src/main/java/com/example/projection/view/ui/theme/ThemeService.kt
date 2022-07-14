package com.example.projection.view.ui.theme

import androidx.compose.material.Colors
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

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
