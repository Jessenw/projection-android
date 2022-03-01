package com.example.projection.data.api.groupbuy

import com.example.projection.data.api.ProjectsResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface GroupbuyEndpoint {

    @GET("groupbuys")
    fun getGroupbuys(): Flowable<ProjectsResponse>

//    @GET("groupbuy/{project_id}")
//    suspend fun getGroupbuy(@Path("project_id") project: String): ProjectPreview
}