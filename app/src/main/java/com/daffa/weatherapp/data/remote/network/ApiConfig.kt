package com.daffa.weatherapp.data.remote.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiConfig {
    companion object {
        fun provideApiService(): ApiService {
            val moshi = MoshiConverterFactory.create()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(moshi)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}