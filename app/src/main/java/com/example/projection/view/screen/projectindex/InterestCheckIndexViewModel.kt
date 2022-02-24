package com.example.projection.view.screen.projectindex

import androidx.lifecycle.*
import com.example.projection.data.model.ProjectPreview
import com.example.projection.data.repository.ProjectionRepository
import com.example.projection.view.component.viewmodel.ListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class InterestCheckIndexViewModel(
     repository: ProjectionRepository = ProjectionRepository()
): ListViewModel, ViewModel() {

     override val dataSource: LiveData<List<ProjectPreview>> =
          repository.requestInterestCheck()
               .observeOn(AndroidSchedulers.mainThread())
               .toLiveData()
}