package com.example.projection.view.screen.profile

import android.app.Application
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.ChevronRight
import androidx.lifecycle.*
import androidx.navigation.NavHostController
import com.example.projection.view.component.standardlist.SimpleStandardListItemViewModel
import com.example.projection.view.component.standardlist.StandardListItemViewModel
import com.example.projection.view.component.standardlist.StandardListViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class ProfileIndexViewModel @AssistedInject constructor(
    application: Application,
    @Assisted navController: NavHostController
) : AndroidViewModel(application), StandardListViewModel {

    private val _dataSource = MutableLiveData<List<StandardListItemViewModel>>()
    override val dataSource: LiveData<List<StandardListItemViewModel>> = _dataSource

    init {
        _dataSource.value = listOf(
            ProfileIndexListItemViewModel(navController, "Change theme", Icons.Filled.Brush),
        )
    }

    @AssistedFactory
    interface Factory {
        fun create(navController: NavHostController): ProfileIndexViewModel
    }

    companion object {
        fun provideFactory(assistedFactory: Factory, navController: NavHostController): ViewModelProvider.Factory {
            return object: ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return assistedFactory.create(navController) as T
                }
            }
        }
    }
}
