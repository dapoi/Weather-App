package com.daffa.weatherapp.data.source

import com.daffa.weatherapp.data.source.local.LocalDataSource
import com.daffa.weatherapp.data.source.local.model.WeatherEntity
import com.daffa.weatherapp.data.source.remote.RemoteDataSource
import com.daffa.weatherapp.data.source.remote.model.CurrentWeatherResponse
import com.daffa.weatherapp.data.source.remote.network.ApiResponse
import com.daffa.weatherapp.data.utils.DataMapper
import com.daffa.weatherapp.vo.Resource
import kotlinx.coroutines.flow.Flow

class WeatherRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IWeatherRepository {
    override fun getCurrentWeather(): Flow<Resource<WeatherEntity>> =
        object : NetworkBoundResource<WeatherEntity, CurrentWeatherResponse>() {
            override fun loadFromDB(): Flow<WeatherEntity> = localDataSource.getCurrentWeather()

            override fun shouldFetch(data: WeatherEntity?): Boolean = data == null

            override suspend fun createCall(): Flow<ApiResponse<CurrentWeatherResponse>> =
                remoteDataSource.getCurrentWeather()

            override suspend fun saveCallResult(data: CurrentWeatherResponse) {
                val dataEntity = DataMapper.mapResponseToEntity(data)
                localDataSource.insertCurrentWeather(dataEntity)
            }
        }.asFlow()

    companion object {
        @Volatile
        private var INSTANCE: WeatherRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource
        ): WeatherRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: WeatherRepository(remoteData, localData)
            }
    }
}