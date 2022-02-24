package com.example.projection.data.api.interestcheck

import com.example.projection.data.api.ProjectsResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface InterestCheckEndpoint {

    @GET("interestchecks")
    fun getInterestChecks(): Flowable<ProjectsResponse>
}