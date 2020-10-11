package com.mobiquitytest.demo.viewmodels

import androidx.lifecycle.ViewModel
import com.mobiquitytest.MyApp
import com.mobiquitytest.demo.data.room.AppRepository
import com.mobiquitytest.demo.data.room.entity.CityEntity

/**
 * Created by Nilesh Pambhar
 */
class MapViewModel : ViewModel() {

    private val repository: AppRepository

    init {
        val appDao = MyApp.instance.appDatabase.appDao()
        repository = AppRepository.getInstance(appDao)

    }

    suspend fun insertCity(cityEntity: CityEntity) {
        repository.insertCity(cityEntity)
    }
}