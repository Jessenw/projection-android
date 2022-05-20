package com.example.projection.data.remote.groupbuy

import com.example.projection.data.remote.model.ProjectsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface GroupbuyRemoteDataSource {
    @GET("groupbuys")
    fun getLatestGroupbuys(): Flow<ProjectsResponse>
}
