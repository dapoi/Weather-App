package com.daffa.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daffa.weatherapp.data.MainRepository
import com.daffa.weatherapp.data.remote.model.CurrentWeatherResponse
import com.daffa.weatherapp.data.remote.network.ApiConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(
    private val mainRepository: MainRepository = MainRepository(ApiConfig.provideApiService())
) : ViewModel() {
    private val _currentWeather = MutableLiveData<CurrentWeatherResponse>()
    var weather: LiveData<CurrentWeatherResponse> = _currentWeather

    init {
        getWeatherData()
    }

    fun getWeatherData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val client = mainRepository.getWeatherData()
                _currentWeather.postValue(client)
                Timber.d("onSuccess $client")
            } catch (e: Exception) {
                Timber.e("onFailure: " + e.message.toString())
            }
        }
    }
}