package com.example.projection.data.repository

import com.example.projection.data.api.ProjectionApi
import com.example.projection.data.model.ProjectPreview
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.emptyFlow

class ProjectionRepository {

    fun requestGroupbuys(): Flowable<List<ProjectPreview>> =
        ProjectionApi.requestGroupbuyApi
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

    fun requestGroupbuy(projectId: String): Flowable<ProjectPreview> =
        ProjectionApi.requestGroupbuyApi
            .getGroupbuy(projectId)
            .subscribeOn(Schedulers.io())
            .onErrorReturn { _ ->
                null
            }
}