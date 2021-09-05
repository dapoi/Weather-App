package com.daffa.weatherapp.data.source.remote.model

import com.squareup.moshi.Json

data class WeatherItem(

    @Json(name = "main")
    val main: String,
)