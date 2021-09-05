package com.daffa.weatherapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.daffa.weatherapp.data.source.WeatherRepository
import com.daffa.weatherapp.data.source.remote.network.ApiConfig
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

//class WeatherRepositoryTest {
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    private lateinit var repository: WeatherRepository
//
//    @Before
//    fun setUp() {
//        repository = WeatherRepository(ApiConfig.provideApiService())
//    }
//
//    @Test
//    fun weatherRepository() = runBlocking {
//        val data = repository.getWeatherData()
//        Assert.assertEquals("Bekasi", data.name)
//    }
//}