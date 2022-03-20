package com.example.projection.data.repository

import android.util.Log
import com.example.projection.data.api.ProjectionApi
import com.example.projection.data.model.ProjectPreview
import com.example.projection.data.persistence.projectpreview.ProjectPreviewRow
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

    fun requestSaved(): Flowable<List<ProjectPreview>> {
        return database.projectPreviews().getAll()
            .flatMap { list ->
                Flowable.fromIterable(list)
                    .map { row -> ProjectPreview.RowMapper.from(row) }
                    .toList()
                    .toFlowable()
                    .subscribeOn(Schedulers.io())
                    .onErrorReturn { _ ->
                        emptyList()
                    }
            }
    }

    fun updateSaved(preview: ProjectPreview) {
        database.projectPreviews().insert(
            ProjectPreviewRow.ModelMapper.from(preview))
    }
}
