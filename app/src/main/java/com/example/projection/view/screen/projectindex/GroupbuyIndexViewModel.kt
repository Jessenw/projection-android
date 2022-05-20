package com.example.projection.view.screen.projectindex

import android.app.Application
import androidx.lifecycle.*
import com.example.projection.ProjectionApp
import com.example.projection.data.repository.GroupbuyRepository
import com.example.projection.data.remote.model.ProjectPreview
import com.example.projection.data.remote.model.Result
import com.example.projection.utilities.Reachability
import com.example.projection.view.component.viewmodel.ListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupbuyIndexViewModel @Inject constructor(
    private val _application: Application,
    private val repository: GroupbuyRepository,
) : AndroidViewModel(_application), ListViewModel  {

    private val _dataSource = MutableLiveData<Result<List<ProjectPreview>>>()
    override var dataSource: LiveData<Result<List<ProjectPreview>>> = _dataSource

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
        TODO("Not yet implemented")
    }
}
