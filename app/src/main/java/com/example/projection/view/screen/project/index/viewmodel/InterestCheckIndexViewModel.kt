package com.example.projection.view.screen.project.index.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.*
import com.example.projection.ProjectionApp
import com.example.projection.data.local.model.ProjectPreviewSaved
import com.example.projection.data.remote.model.ProjectPreview
import com.example.projection.data.remote.model.Result
import com.example.projection.data.repository.InterestCheckRepository
import com.example.projection.utilities.Reachability
import com.example.projection.view.components.viewmodel.ListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InterestCheckIndexViewModel @Inject constructor(
     application: Application,
     private val repository: InterestCheckRepository
) : AndroidViewModel(application), ListViewModel  {

     private val _dataSource = MutableLiveData<Result<List<ProjectPreview>>>()
     override var dataSource: LiveData<Result<List<ProjectPreview>>> = _dataSource

     @SuppressLint("StaticFieldLeak")
     private val context = getApplication<ProjectionApp>().applicationContext

     init {
          getLatestInterestChecks()
     }

     private fun getLatestInterestChecks() {
          viewModelScope.launch {
               val refresh = Reachability.isInternetConnected(context)
               repository.getLatestInterestChecks(refresh).collect {
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
