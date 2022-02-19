package com.example.projection

import androidx.lifecycle.*
import com.example.projection.data.model.ProjectPreview
import com.example.projection.data.repository.ProjectionRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class MainActivityViewModel(repository: ProjectionRepository = ProjectionRepository()) : ViewModel() {

     val dataSource: LiveData<List<ProjectPreview>> =
          repository.requestGroupbuys()
               .observeOn(AndroidSchedulers.mainThread())
               .toLiveData()
}