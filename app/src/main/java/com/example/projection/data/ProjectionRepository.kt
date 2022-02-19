package com.example.projection.data

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers

class ProjectionRepository {

    fun requestGroupbuys(): Flowable<List<ProjectPreview>> =
        ProjectionApi.requestApi
            .getGroupbuys()
            .map { it.projects }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn { _ ->
                emptyList()
            }
}