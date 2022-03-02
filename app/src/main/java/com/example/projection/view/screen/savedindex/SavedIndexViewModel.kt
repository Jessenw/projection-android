package com.example.projection.view.screen.savedindex

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.example.projection.data.model.ProjectPreview
import com.example.projection.data.persistence.projectpreview.ProjectPreviewDao
import com.example.projection.data.persistence.projectpreview.ProjectPreviewRow
import com.example.projection.view.component.viewmodel.ListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable

class SavedIndexViewModel(
    repository: SavedIndexRepository = SavedIndexRepository()
) : ListViewModel, ViewModel() {

    override val dataSource: LiveData<List<ProjectPreview>> =
        repository.allProjects
            .observeOn(AndroidSchedulers.mainThread())
            .toLiveData()

}

class SavedIndexRepository {

    val allProjects: Flowable<List<ProjectPreviewRow>> = ProjectPreviewDao.getAll()
}