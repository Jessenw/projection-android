package com.example.projection.view.ui.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projection.data.repository.UserConfigurationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ThemeViewModel {
    val theme: LiveData<Int>
}

@HiltViewModel
class ThemeViewModelImpl @Inject constructor(
    private val repository: UserConfigurationRepository
) : ViewModel(), ThemeViewModel {

    private val _theme = MutableLiveData<Int>()
    override var theme: LiveData<Int> = _theme

    init {
        viewModelScope.launch {
            repository.getLatestUserConfiguration().collect {
                _theme.value = it.theme
                theme = _theme
            }
        }
    }
}
