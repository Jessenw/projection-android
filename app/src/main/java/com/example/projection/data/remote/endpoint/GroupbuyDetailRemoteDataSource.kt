package com.example.projection.data.remote.endpoint

import com.example.projection.data.remote.model.ProjectDetail
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface GroupbuyDetailRemoteDataSource {
    @GET("groupbuy/{project_id}")
    fun getGroupbuy(@Path("project_id") projectId: String): Flow<ProjectDetail>
}
