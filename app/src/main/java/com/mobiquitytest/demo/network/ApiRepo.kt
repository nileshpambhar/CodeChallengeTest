package com.mobiquitytest.demo.network

import com.mobiquitytest.demo.data.response.TodayWeatherResponse
import com.mobiquitytest.demo.utils.Constants.API_KEY
import com.mobiquitytest.demo.utils.Constants.METRIC
import retrofit2.Call
import retrofit2.Callback

class ApiRepo {

    var endPoints: ApiClient = ApiClient()

    fun getTodayForecast(lat: String, lan: String, callback: Callback<TodayWeatherResponse>) {
        val apiService = endPoints.getClient()!!.create(ApiService::class.java)
        val call: Call<TodayWeatherResponse> =
            apiService.getTodayForecast(lat, lan, METRIC, API_KEY)
        call.enqueue(callback)
    }
}