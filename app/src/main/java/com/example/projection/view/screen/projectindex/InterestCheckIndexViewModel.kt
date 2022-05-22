package com.example.projection.view.screen.projectindex

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.*
import com.example.projection.ProjectionApp
import com.example.projection.data.remote.model.ProjectPreview
import com.example.projection.data.remote.model.Result
import com.example.projection.data.repository.InterestCheckRepository
import com.example.projection.utilities.Reachability
import com.example.projection.view.component.viewmodel.ListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
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
          TODO("Not yet implemented")
     }
}
