package com.daffa.weatherapp.data.source.remote.model

import com.squareup.moshi.Json

data class Main(

    @Json(name = "temp")
    val temp: Double,

    @Json(name = "humidity")
    val humidity: Int,

    @Json(name = "pressure")
    val pressure: Int,
)