package com.example.projection.view.screen.project.index.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.*
import com.example.projection.ProjectionApp
import com.example.projection.data.local.model.ProjectPreviewSaved
import com.example.projection.data.remote.model.ProjectPreview
import com.example.projection.data.remote.model.Result
import com.example.projection.data.repository.GroupbuyIndexRepository
import com.example.projection.utilities.Reachability
import com.example.projection.view.components.viewmodel.ListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupbuyIndexViewModel @Inject constructor(
    application: Application,
    private val repository: GroupbuyIndexRepository,
) : AndroidViewModel(application), ListViewModel  {

    private val _dataSource = MutableLiveData<Result<List<ProjectPreview>>>()
    override var dataSource: LiveData<Result<List<ProjectPreview>>> = _dataSource

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<ProjectionApp>().applicationContext

    init {
        getLatestGroupbuys()
    }

    private fun getLatestGroupbuys() {
        viewModelScope.launch {
            val refresh = Reachability.isInternetConnected(context)
            repository.getLatestGroupbuys(refresh).collect {
                _dataSource.value = it
                dataSource = _dataSource
            }
        }
    }

    override fun tappedSave(preview: ProjectPreview, saved: Boolean) {
        viewModelScope.launch {
            val savedPreview = ProjectPreviewSaved(preview.id, preview.type, saved)
            repository.updateSaved(savedPreview)
        }
    }
}
