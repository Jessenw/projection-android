package com.example.projection.view.screen.project.detail.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.*
import com.example.projection.ProjectionApp
import com.example.projection.data.remote.model.*
import com.example.projection.data.repository.GroupbuyDetailRepository
import com.example.projection.data.repository.GroupbuyDetailRepositoryFactory
import com.example.projection.utilities.Reachability
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

interface GroupbuyDetailViewModel {
    val dataSource: LiveData<Result<ProjectDetail>>
}

@HiltViewModel
class GroupbuyDetailViewModelImpl @Inject constructor(
    savedStateHandle: SavedStateHandle,
    application: Application,
    repositoryFactory: GroupbuyDetailRepositoryFactory
) : AndroidViewModel(application), GroupbuyDetailViewModel {

    private var repository: GroupbuyDetailRepository? = null

    private val _dataSource = MutableLiveData<Result<ProjectDetail>>()
    override var dataSource: LiveData<Result<ProjectDetail>> = _dataSource

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<ProjectionApp>().applicationContext

    init {
        println("SavedStateHandle > Keys ${savedStateHandle.keys()}")
        println("SavedStateHandle > project_id ${savedStateHandle.get<String>("project_id") }")

        val projectId = savedStateHandle.get<String>("project_id")
        projectId?.let { repository = repositoryFactory.create(projectId) }

        getGroupbuy()
    }

    private fun getGroupbuy() {
        viewModelScope.launch {
            val refresh = Reachability.isInternetConnected(context)
            if (repository != null) {
                repository!!.getGroupbuy(refresh).collect {
                    _dataSource.value = it
                    dataSource = _dataSource
                }
            }
        }
    }
}

