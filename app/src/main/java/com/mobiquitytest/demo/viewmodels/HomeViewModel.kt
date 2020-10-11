package com.mobiquitytest.demo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mobiquitytest.MyApp
import com.mobiquitytest.demo.data.room.AppRepository
import com.mobiquitytest.demo.data.room.entity.CityEntity

/**
 * Created by Nilesh Pambhar
 */
class HomeViewModel : ViewModel() {

    private val repository: AppRepository
    var cityList: LiveData<List<CityEntity>>

    init {
        val appDao = MyApp.instance.appDatabase.appDao()
        repository = AppRepository.getInstance(appDao)
        cityList = repository.getAllCities()
    }

    suspend fun insertDefaultCity() {
        val cityEntity = CityEntity(0)
        repository.insertCity(cityEntity)
    }

    suspend fun deleteCity(cityEntity: CityEntity) {
        repository.deleteCity(cityEntity)
    }
}