package com.daffa.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daffa.weatherapp.data.remote.model.CurrentWeatherResponse
import com.daffa.weatherapp.data.remote.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainViewModel : ViewModel() {
    private val _currentWeather = MutableLiveData<CurrentWeatherResponse>()
    var weather: LiveData<CurrentWeatherResponse> = _currentWeather

    init {
        getWeatherData()
    }

    private fun getWeatherData() {
        val weather = ApiConfig.provideApiService().getWeather()
        weather.enqueue(object : Callback<CurrentWeatherResponse> {
            override fun onResponse(
                call: Call<CurrentWeatherResponse>,
                response: Response<CurrentWeatherResponse>
            ) {
                if (response.isSuccessful) {
                    _currentWeather.value = response.body()
                    Timber.d("onSuccess" + response.message())
                } else {
                    Timber.e("onFailure: " + response.message())
                }
            }

            override fun onFailure(call: Call<CurrentWeatherResponse>, t: Throwable) {
                Timber.e("onFailure: " + t.message.toString())
            }

        })
    }
}