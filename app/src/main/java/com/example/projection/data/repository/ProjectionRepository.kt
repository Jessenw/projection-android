package com.example.projection.data.repository

import androidx.lifecycle.toLiveData
import com.example.projection.data.api.ProjectionApi
import com.example.projection.data.model.ProjectPreview
import com.example.projection.data.persistence.ProjectPreviewDao
import com.example.projection.data.persistence.ProjectPreviewRow
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers

class ProjectionRepository {

    fun requestGroupbuys(): Flowable<List<ProjectPreview>> =
        ProjectionApi.requestGroupbuyEndpoint
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

    fun requestSaved(dao: ProjectPreviewDao): Flowable<List<ProjectPreviewRow>> =
        dao.getAll()
}
