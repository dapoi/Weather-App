package com.daffa.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.daffa.weatherapp.data.source.WeatherRepository
import com.daffa.weatherapp.data.source.local.model.WeatherEntity
import com.daffa.weatherapp.vo.Resource

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    fun getCurrentWeather(): LiveData<Resource<WeatherEntity>> =
        weatherRepository.getCurrentWeather().asLiveData()
}