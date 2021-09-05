package com.daffa.weatherapp.data.source.remote

import com.daffa.weatherapp.data.source.remote.model.CurrentWeatherResponse
import com.daffa.weatherapp.data.source.remote.network.ApiResponse
import com.daffa.weatherapp.data.source.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

@Suppress("DataClassPrivateConstructor")
data class RemoteDataSource private constructor(private val apiService: ApiService) {

    fun getCurrentWeather(): Flow<ApiResponse<CurrentWeatherResponse>> =
        flow {
            try {
                val response = apiService.getWeather()
                emit(ApiResponse.Success(response))
                Timber.d("onSuccess $response")
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Timber.e("onFailure " + e.message.toString())
            }
        }.flowOn(Dispatchers.IO)

    companion object {

        @Volatile
        private var INSTANCE: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: RemoteDataSource(apiService)
            }
    }
}
