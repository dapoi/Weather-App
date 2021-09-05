package com.daffa.weatherapp.di

import android.content.Context
import com.daffa.weatherapp.data.source.WeatherRepository
import com.daffa.weatherapp.data.source.local.LocalDataSource
import com.daffa.weatherapp.data.source.local.room.WeatherDatabase
import com.daffa.weatherapp.data.source.remote.RemoteDataSource
import com.daffa.weatherapp.data.source.remote.network.ApiConfig

object Injection {

    fun provideRepository(context: Context): WeatherRepository {
        val db = WeatherDatabase.getInstance(context)

        val remote = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val local = LocalDataSource.getInstance(db.weatherDao())

        return WeatherRepository.getInstance(remote, local)
    }
}