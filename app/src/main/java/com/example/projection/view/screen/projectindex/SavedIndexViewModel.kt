package com.example.projection.view.screen.projectindex

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.toLiveData
import com.example.projection.ProjectionApp
import com.example.projection.data.model.ProjectPreview
import com.example.projection.view.component.viewmodel.ListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class SavedIndexViewModel(
    application: Application
) : ListViewModel, AndroidViewModel(application) {

    private val repository = getApplication<ProjectionApp>().projectPreviewRepository

    override val dataSource: LiveData<List<ProjectPreview>> =
        repository.requestSaved()
            .observeOn(AndroidSchedulers.mainThread())
            .toLiveData()

    override fun tappedSave(preview: ProjectPreview) {
        repository.updateSaved(preview)
    }
}