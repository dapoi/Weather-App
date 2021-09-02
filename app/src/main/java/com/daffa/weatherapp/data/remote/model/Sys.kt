package com.daffa.weatherapp.data.remote.model

import com.squareup.moshi.Json

data class Sys(

	@Json(name="country")
	val country: String,

	@Json(name="sunrise")
	val sunrise: Int,

	@Json(name="sunset")
	val sunset: Int,

	@Json(name="id")
	val id: Int,

	@Json(name="type")
	val type: Int
)