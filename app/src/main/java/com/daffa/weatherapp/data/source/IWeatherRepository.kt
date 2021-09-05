package com.daffa.weatherapp.data.source

import com.daffa.weatherapp.data.source.local.model.WeatherEntity
import com.daffa.weatherapp.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IWeatherRepository {

    fun getCurrentWeather(): Flow<Resource<WeatherEntity>>
}