package com.example.weathertrackapplication.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("daily")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("cnt") cnt: Int = 7,
        @Query("appId") appId: String = "60c6fbeb4b93ac653c492ba806fc346d"
    ): Response<Weather>
}