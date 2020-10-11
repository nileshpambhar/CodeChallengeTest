package com.mobiquitytest.demo.data.room.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "City")
@Parcelize
data class CityEntity(
    @PrimaryKey(autoGenerate = true) val _id: Int,
    val name: String = "Ahmedabad",
    val latitude: String = "23.0225",
    val longitude: String = "72.5714"
) : Parcelable {
    fun getInfo(): String {
        return String.format("%.4f,%.4f", latitude.toDouble(), longitude.toDouble())
    }
}