package com.example.projection.view.screen.themeindex

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.projection.data.remote.model.UserConfiguration
import com.example.projection.data.repository.UserConfigurationRepository
import com.example.projection.view.components.standardlist.StandardListItemViewModel
import com.example.projection.view.components.standardlist.ToggleIconViewModel
import com.example.projection.view.ui.theme.Palette
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ThemeIndexListItemViewModel(
    private val repository: UserConfigurationRepository,
    private val palette: Palette
) : StandardListItemViewModel {

    override val title: Int = palette.nameId
    override val startIcon: ImageVector? = null
    override val endIcon: ImageVector? = null
    override val endToggleIcon: ToggleIconViewModel =
        ToggleIconViewModel(
            Icons.Filled.RadioButtonUnchecked,
            Icons.Filled.RadioButtonChecked)

    override fun tapped() {
        CoroutineScope(Dispatchers.IO).launch {
            val configurationTheme = UserConfiguration("1001", palette.nameId)
            repository.updateTheme(configurationTheme)
        }
    }
}
