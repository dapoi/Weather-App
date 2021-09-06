package com.daffa.weatherapp

import androidx.lifecycle.MutableLiveData
import com.daffa.weatherapp.data.source.local.model.WeatherEntity
import com.daffa.weatherapp.vo.Resource

object DataDummy {

    fun getWeatherData() = WeatherEntity(
        0, "", 0.0, 0, 0, 0.0, "", 0, 0
    )

    fun getWeatherLiveData(): MutableLiveData<Resource<WeatherEntity>> {
        val weather = MutableLiveData<Resource<WeatherEntity>>()
        weather.value = Resource.Success(getWeatherData())

        return weather
    }
}