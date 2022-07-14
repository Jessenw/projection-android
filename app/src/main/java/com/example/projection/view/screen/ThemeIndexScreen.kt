package com.example.projection.view.screen

import android.app.Application
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.example.projection.view.component.standardlist.SimpleStandardListItemViewModel
import com.example.projection.view.component.standardlist.StandardListItemViewModel
import com.example.projection.view.component.standardlist.StandardListView
import com.example.projection.view.component.standardlist.StandardListViewModel
import com.example.projection.view.ui.theme.Palette
import com.example.projection.view.ui.theme.ThemeService
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Composable
fun ThemeIndexScreen(
    navController: NavHostController,
    viewModel: ThemeIndexViewModel = hiltViewModel()
) {
    StandardListView(navController, viewModel)
}

@HiltViewModel
class ThemeIndexViewModel @Inject constructor(
    application: Application,
    private val themeService: ThemeService
) : AndroidViewModel(application), StandardListViewModel {

    private val _dataSource = MutableLiveData<List<StandardListItemViewModel>>()
    override val dataSource: LiveData<List<StandardListItemViewModel>> = _dataSource

    init {
        _dataSource.value = listOf(
            ThemeIndexListItemViewModel(themeService, Palette.OliviaColorScheme, "Olivia"),
            ThemeIndexListItemViewModel(themeService, Palette.BotanicalColorScheme,  "Botanical")
        )
    }
}

class ThemeIndexListItemViewModel(
    private val themeService: ThemeService,
    private val palette: Palette,
    override val title: String,
    override val startIcon: ImageVector? = null,
    override val endIcon: ImageVector? = Icons.Filled.RadioButtonUnchecked
) : StandardListItemViewModel {

    override fun tapped() {
        themeService.updateTheme(palette)
    }
}


