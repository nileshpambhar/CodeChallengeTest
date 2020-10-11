package com.mobiquitytest.demo.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mobiquitytest.demo.data.room.entity.CityEntity

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCity(city: CityEntity)

    @Query("Select * from City")
    fun getAllCities(): LiveData<List<CityEntity>>

    @Query("Delete from City")
    suspend fun deleteAllCities()

    @Delete()
    suspend fun deleteCity(city: CityEntity)
}