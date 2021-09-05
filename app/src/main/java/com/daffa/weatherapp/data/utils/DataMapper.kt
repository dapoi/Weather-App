package com.daffa.weatherapp.data.utils

import com.daffa.weatherapp.data.source.local.model.WeatherEntity
import com.daffa.weatherapp.data.source.remote.model.CurrentWeatherResponse

object DataMapper {
    fun mapResponseToEntity(dataResponse: CurrentWeatherResponse): WeatherEntity = WeatherEntity(
        0,
        dataResponse.name,
        dataResponse.main.temp,
        dataResponse.main.humidity,
        dataResponse.main.pressure,
        dataResponse.wind.speed,
        dataResponse.weather[0].main,
        dataResponse.sys.sunrise,
        dataResponse.sys.sunset
    )
}