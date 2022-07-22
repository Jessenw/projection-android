package com.example.projection.data.remote.endpoint

import com.example.projection.data.remote.model.ProjectsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface GroupbuyIndexRemoteDataSource {

    @GET("groupbuys")
    fun getLatestGroupbuys(): Flow<ProjectsResponse>
}
