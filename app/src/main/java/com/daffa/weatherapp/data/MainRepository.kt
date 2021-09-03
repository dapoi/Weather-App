package com.daffa.weatherapp.data

import com.daffa.weatherapp.data.remote.network.ApiService

class MainRepository(private val apiService: ApiService) {

    suspend fun getWeatherData() = apiService.getWeather()

}