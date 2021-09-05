package com.daffa.weatherapp.data.source.remote.model

import com.squareup.moshi.Json

data class Wind(

    @Json(name = "speed")
    val speed: Double
)