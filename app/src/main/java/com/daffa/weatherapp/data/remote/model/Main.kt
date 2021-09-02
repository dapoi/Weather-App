package com.daffa.weatherapp.data.remote.model

import com.squareup.moshi.Json

data class Main(

	@Json(name="temp")
	val temp: Double,

	@Json(name="temp_min")
	val tempMin: Double,

	@Json(name="humidity")
	val humidity: Int,

	@Json(name="pressure")
	val pressure: Int,

	@Json(name="feels_like")
	val feelsLike: Double,

	@Json(name="temp_max")
	val tempMax: Double
)