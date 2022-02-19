package com.example.projection.data

import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface ProjectionEndpoint {

    @GET("groupbuys")
    fun getGroupbuys(): Flowable<ProjectsResponse>

//    @GET("groupbuy/{project_id}")
//    suspend fun getGroupbuy(@Path("project_id") project: String): ProjectPreview
}