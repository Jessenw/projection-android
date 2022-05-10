package com.example.projection.data.remote.groupbuy

import com.example.projection.data.remote.model.ProjectsResponse
import retrofit2.http.GET

interface GroupbuyRemoteDataSource {
    @GET("groupbuys")
    suspend fun getLatestGroupbuys(): ProjectsResponse
}
