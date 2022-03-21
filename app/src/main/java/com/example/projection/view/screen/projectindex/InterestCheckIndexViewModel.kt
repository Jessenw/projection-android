package com.example.projection.view.screen.projectindex

import android.app.Application
import androidx.lifecycle.*
import com.example.projection.ProjectionApp
import com.example.projection.data.model.ProjectPreview
import com.example.projection.view.component.viewmodel.ListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.launch

class InterestCheckIndexViewModel(
     application: Application
): ListViewModel, AndroidViewModel(application) {

     private val repository = getApplication<ProjectionApp>().projectPreviewRepository

     override val dataSource: LiveData<List<ProjectPreview>> =
          repository.requestInterestCheck()
               .observeOn(AndroidSchedulers.mainThread())
               .toLiveData()

     override fun tappedSave(preview: ProjectPreview) {
          viewModelScope.launch {
               repository.updateSaved(preview)
          }
     }
}
