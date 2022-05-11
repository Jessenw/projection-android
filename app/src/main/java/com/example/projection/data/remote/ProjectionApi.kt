package com.example.projection.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ProjectionApi {

    private const val BASE_URL_LOCAL = "http://10.0.2.2:8000/"

    private val logging = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val httpClient = OkHttpClient().newBuilder()
        .addInterceptor(logging)

    private val apiBuilder: Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL_LOCAL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(httpClient.build())
            .build()

//    val requestGroupbuyApi: GroupbuyEndpoint by lazy {
//        apiBuilder.create(GroupbuyEndpoint::class.java)
//    }
//
//    val requestInterestCheckApi: InterestCheckEndpoint by lazy {
//        apiBuilder.create(InterestCheckEndpoint::class.java)
//    }
}