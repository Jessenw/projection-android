package com.example.projection.data.api.groupbuy

import com.example.projection.data.api.ProjectsResponse
import com.example.projection.data.model.ProjectPreview
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface GroupbuyEndpoint {

    @GET("groupbuys")
    fun getGroupbuys(): Flowable<ProjectsResponse>

    @GET("groupbuy/{project_id}")
    fun getGroupbuy(@Path("project_id") projectId: String): Flowable<ProjectPreview>
}