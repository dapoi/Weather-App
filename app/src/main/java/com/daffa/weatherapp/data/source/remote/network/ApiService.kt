package com.daffa.weatherapp.data.source.remote.network

import com.daffa.weatherapp.data.source.remote.model.CurrentWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") cityName: String = "Bekasi",
        @Query("appid") apiKey: String = API_KEY
    ): CurrentWeatherResponse

    companion object {
        private const val API_KEY = "ed7334f70bf4e86eac671cb25e77fb90"
    }

}