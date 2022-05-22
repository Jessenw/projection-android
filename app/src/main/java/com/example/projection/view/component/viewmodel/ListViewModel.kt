package com.example.projection.view.component.viewmodel

import androidx.lifecycle.LiveData
import com.example.projection.data.remote.model.ProjectPreview
import com.example.projection.data.remote.model.Result

interface ListViewModel {
    val dataSource: LiveData<Result<List<ProjectPreview>>>

    fun tappedSave(preview: ProjectPreview, saved: Boolean)
}
