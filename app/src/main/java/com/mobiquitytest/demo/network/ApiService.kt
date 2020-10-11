package com.mobiquitytest.demo.network

import com.mobiquitytest.demo.data.response.TodayWeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("weather")
    fun getTodayForecast(
        @Query("lat") lat: String,
        @Query("lon") lan: String,
        @Query("units") units: String,
        @Query("appid") appId: String
    ): Call<TodayWeatherResponse>
}