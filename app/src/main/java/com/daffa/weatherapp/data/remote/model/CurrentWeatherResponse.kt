package com.daffa.weatherapp.data.remote.model

import com.squareup.moshi.Json

data class CurrentWeatherResponse(

	@Json(name="weather")
	val weather: List<WeatherItem>,

	@Json(name="name")
	val name: String,

	@Json(name="main")
	val main: Main,

	@Json(name="sys")
	val sys: Sys,

	@Json(name="wind")
	val wind: Wind
)