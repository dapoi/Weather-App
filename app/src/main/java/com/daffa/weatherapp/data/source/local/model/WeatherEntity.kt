package com.daffa.weatherapp.data.source.local.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherEntity(

    @ColumnInfo(name = "id")
    @NonNull
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "temp")
    val temp: Double? = null,

    @ColumnInfo(name = "humidity")
    val humidity: Int? = null,

    @ColumnInfo(name = "pressure")
    val pressure: Int? = null,

    @ColumnInfo(name = "speed")
    val speed: Double? = null,

    @ColumnInfo(name = "main")
    val weatherInfo: String? = null,

    @ColumnInfo(name = "sunrise")
    val sunrise: Int? = null,

    @ColumnInfo(name = "sunset")
    val sunset: Int? = null,
)
