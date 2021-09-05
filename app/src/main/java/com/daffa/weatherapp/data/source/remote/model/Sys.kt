package com.daffa.weatherapp.data.source.remote.model

import com.squareup.moshi.Json

data class Sys(

    @Json(name = "sunrise")
    val sunrise: Int,

    @Json(name = "sunset")
    val sunset: Int,
)