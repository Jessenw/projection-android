package com.example.projection.view.screen.projectindex

import androidx.lifecycle.*
import com.example.projection.data.repository.GroupbuyRepository
import com.example.projection.data.remote.model.ProjectPreview
import com.example.projection.data.remote.model.Result
import com.example.projection.view.component.viewmodel.ListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class SavedIndexViewModel @Inject constructor(
    private val repository: GroupbuyRepository
) : ViewModel(), ListViewModel  {

    private val _dataSource = MutableLiveData<Result<List<ProjectPreview>>>()
    override var dataSource: LiveData<Result<List<ProjectPreview>>> = _dataSource

    suspend fun getLatestGroupbuys() {
        repository.getLatestGroupbuys(true).collect {
            _dataSource.value = it
            dataSource = _dataSource
        }
    }

    override fun tappedSave(preview: ProjectPreview, saved: Boolean) {
        TODO("Not yet implemented")
    }
}
