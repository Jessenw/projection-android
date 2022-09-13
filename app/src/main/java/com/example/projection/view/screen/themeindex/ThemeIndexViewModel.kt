package com.example.projection.view.screen.themeindex

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.projection.data.repository.UserConfigurationRepository
import com.example.projection.view.component.standardlist.StandardListItemViewModel
import com.example.projection.view.component.standardlist.StandardListViewModel
import com.example.projection.view.ui.theme.Palette
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

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
