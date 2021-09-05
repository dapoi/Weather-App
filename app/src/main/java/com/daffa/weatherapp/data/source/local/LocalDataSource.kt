package com.daffa.weatherapp.data.source.local

import com.daffa.weatherapp.data.source.local.model.WeatherEntity
import com.daffa.weatherapp.data.source.local.room.WeatherDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val weatherDao: WeatherDao) {

    fun getCurrentWeather(): Flow<WeatherEntity> = weatherDao.getCurrentWeather()

    suspend fun insertCurrentWeather(weatherEntity: WeatherEntity) =
        weatherDao.insertWeather(weatherEntity)

    companion object {

        @Volatile
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(weatherDao: WeatherDao): LocalDataSource =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: LocalDataSource(weatherDao)
            }
    }
}