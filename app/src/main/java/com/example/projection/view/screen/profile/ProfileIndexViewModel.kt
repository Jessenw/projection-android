package com.example.projection.view.screen.profile

import android.app.Application
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.ChevronRight
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.projection.view.component.standardlist.SimpleStandardListItemViewModel
import com.example.projection.view.component.standardlist.StandardListItemViewModel
import com.example.projection.view.component.standardlist.StandardListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileIndexViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application), StandardListViewModel {

    private val _dataSource = MutableLiveData<List<StandardListItemViewModel>>()
    override val dataSource: LiveData<List<StandardListItemViewModel>> = _dataSource

    init {
        _dataSource.value = listOf(
            SimpleStandardListItemViewModel("Change theme", Icons.Filled.Brush, Icons.Filled.ChevronRight)
        )
    }
}
