package com.example.projection.view.screen.savedindex

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.example.projection.data.model.ProjectPreview
import com.example.projection.data.repository.ProjectionRepository
import com.example.projection.view.component.viewmodel.ListViewModel

class SavedIndexViewModel(
    repository: ProjectionRepository = ProjectionRepository(),
) : ListViewModel, ViewModel() {

    // TODO: Update to retrieve saved projects from database
    override val dataSource: LiveData<List<ProjectPreview>> =
        repository.requestInterestCheck().toLiveData()
}
