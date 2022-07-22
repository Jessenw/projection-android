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
import com.example.projection.data.local.model.UserConfigurationRow
import com.example.projection.data.local.model.UserConfigurationTheme
import com.example.projection.data.remote.model.UserConfiguration
import com.example.projection.view.component.standardlist.StandardListItemViewModel
import com.example.projection.view.component.standardlist.StandardListView
import com.example.projection.view.component.standardlist.StandardListViewModel
import com.example.projection.view.ui.theme.Palette
import com.example.projection.data.repository.UserConfigurationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
    repository: UserConfigurationRepository
) : AndroidViewModel(application), StandardListViewModel {

    private val _dataSource = MutableLiveData<List<StandardListItemViewModel>>()
    override val dataSource: LiveData<List<StandardListItemViewModel>> = _dataSource

    init {
        _dataSource.value = listOf(
            ThemeIndexListItemViewModel(repository, Palette.Botanical),
            ThemeIndexListItemViewModel(repository, Palette.EightOhOhEight),
            ThemeIndexListItemViewModel(repository, Palette.Olivia),
        )
    }
}

class ThemeIndexListItemViewModel(
    private val repository: UserConfigurationRepository,
    private val palette: Palette,
) : StandardListItemViewModel {

    override val title: Int = palette.nameId
    override val startIcon: ImageVector? = null
    override val endIcon: ImageVector = Icons.Filled.RadioButtonUnchecked

    override fun tapped() {
        CoroutineScope(Dispatchers.IO).launch {
            val configurationTheme = UserConfiguration("1001", palette.nameId)
            repository.updateTheme(configurationTheme)
        }
    }
}


