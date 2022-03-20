package com.example.projection.data.repository

import androidx.lifecycle.toLiveData
import com.example.projection.data.api.ProjectionApi
import com.example.projection.data.model.ProjectPreview
import com.example.projection.data.persistence.projectpreview.ProjectionDatabase
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers

class ProjectionRepository(
    private val database: ProjectionDatabase
) {

    fun requestGroupbuys(): Flowable<List<ProjectPreview>> =
        ProjectionApi.requestApi
            .getGroupbuys()
            .map { it.projects }
            .subscribeOn(Schedulers.io())
            .onErrorReturn { _ ->
                emptyList()
            }

    fun requestInterestCheck(): Flowable<List<ProjectPreview>> =
        ProjectionApi.requestInterestCheckApi
            .getInterestChecks()
            .map { it.projects }
            .subscribeOn(Schedulers.io())
            .onErrorReturn { _ ->
                emptyList()
            }

//    fun requestSaved(): Flowable<List<ProjectPreview>> =
//        database
//            .projectPreviews
//            .getAll()
}
