package com.mobiquitytest.demo.data.room

import androidx.lifecycle.LiveData
import com.mobiquitytest.demo.data.room.entity.CityEntity

class AppRepository internal constructor(private val appDao: AppDao) {
    companion object {
        @Volatile
        private var instance: AppRepository? = null

        fun getInstance(appDao: AppDao): AppRepository {
            return instance ?: synchronized(this) {
                instance ?: AppRepository(appDao).also { instance = it }
            }
        }
    }

    suspend fun insertCity(city: CityEntity) {
        appDao.insertCity(city)
    }

    fun getAllCities(): LiveData<List<CityEntity>> {
        return appDao.getAllCities()
    }

    suspend fun deleteAllCities() {
        appDao.deleteAllCities()
    }

    suspend fun deleteCity(city: CityEntity) {
        appDao.deleteCity(city)
    }
}