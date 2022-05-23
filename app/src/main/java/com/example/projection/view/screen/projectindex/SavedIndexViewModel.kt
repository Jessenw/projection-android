package com.example.projection.view.screen.projectindex

import androidx.lifecycle.*
import com.example.projection.data.local.model.ProjectPreviewSaved
import com.example.projection.data.remote.model.ProjectPreview
import com.example.projection.data.remote.model.Result
import com.example.projection.data.repository.SavedIndexRepository
import com.example.projection.view.component.viewmodel.ListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedIndexViewModel @Inject constructor(
    private val repository: SavedIndexRepository
) : ViewModel(), ListViewModel  {

    private val _dataSource = MutableLiveData<Result<List<ProjectPreview>>>()
    override var dataSource: LiveData<Result<List<ProjectPreview>>> = _dataSource

    init {
        getLatestSaved()
    }

    private fun getLatestSaved() {
        viewModelScope.launch {
            repository.getLatestSaved().collect {
                _dataSource.value = it
                dataSource = _dataSource
            }
        }
    }

    override fun tappedSave(preview: ProjectPreview, saved: Boolean) {
        viewModelScope.launch {
            val saved = ProjectPreviewSaved(preview.id, preview.type, saved)
            repository.updateSaved(saved)
        }
    }
}
