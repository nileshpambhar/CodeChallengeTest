package com.mobiquitytest.demo.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobiquitytest.demo.data.response.TodayWeatherResponse
import com.mobiquitytest.demo.network.ApiRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Nilesh Pambhar
 */
class CityViewModel : ViewModel() {

    var status: MutableLiveData<Int>? = null
    private var apiRepo = ApiRepo()
    var todayWeatherResponse: TodayWeatherResponse? = null

    init {

        status = MutableLiveData()
    }

    /**
     * getTodayWeather, api call to get today's weather forecast
     */
    fun getTodayWeather(lat: String, lan: String) {
        apiRepo.getTodayForecast(lat, lan, object : Callback<TodayWeatherResponse> {
            override fun onFailure(call: Call<TodayWeatherResponse>, t: Throwable) {
                status?.postValue(0)
            }

            override fun onResponse(
                call: Call<TodayWeatherResponse>,
                response: Response<TodayWeatherResponse>
            ) {

                if (response.isSuccessful) {
                    status?.postValue(1)
                    todayWeatherResponse = response.body()
                } else {
                    status?.postValue(response.code())
                }
            }

        })
    }
}