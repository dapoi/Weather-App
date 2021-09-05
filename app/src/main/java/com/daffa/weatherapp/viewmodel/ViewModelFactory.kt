package com.daffa.weatherapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.daffa.weatherapp.data.source.WeatherRepository
import com.daffa.weatherapp.di.Injection

class ViewModelFactory private constructor(private val weatherRepository: WeatherRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(WeatherViewModel::class.java) -> {
                WeatherViewModel(weatherRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel Class: " + modelClass.name)
        }

    companion object {

        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideRepository(context)
                )
            }
    }
}