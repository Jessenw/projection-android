package com.example.projection.view.component.viewmodel

import androidx.lifecycle.LiveData
import com.example.projection.data.model.ProjectPreview

interface ListViewModel {
    val dataSource: LiveData<List<ProjectPreview>>

    fun tappedSave(preview: ProjectPreview, saved: Boolean)
}
