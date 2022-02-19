package com.example.projection

import androidx.lifecycle.*
import com.example.projection.data.ProjectPreview
import com.example.projection.data.ProjectionRepository

class MainActivityViewModel(repository: ProjectionRepository = ProjectionRepository()) : ViewModel() {

     val dataSource: LiveData<List<ProjectPreview>> =
          repository.requestGroupbuys()
               .toLiveData()
}