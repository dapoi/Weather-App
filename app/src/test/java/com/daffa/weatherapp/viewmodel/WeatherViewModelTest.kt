package com.daffa.weatherapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asFlow
import com.daffa.weatherapp.data.source.WeatherRepository
import com.daffa.weatherapp.data.source.local.model.WeatherEntity
import com.daffa.weatherapp.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

//@RunWith(MockitoJUnitRunner::class)
//class WeatherViewModelTest {
//
//    @Mock
//    private lateinit var weatherViewModel: WeatherViewModel
//
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//
//    @Mock
//    private lateinit var repository: WeatherRepository
//
//    @Mock
//    private lateinit var weatherEntity: WeatherEntity
//
//    @Mock
//    private lateinit var weatherObserver: Observer<Resource<WeatherEntity>>
//
//    @Before
//    fun setUp() {
//        weatherViewModel = WeatherViewModel(repository)
//    }
//
//    @Test
//    fun testGetWeatherData() = runBlocking(Dispatchers.IO) {
//        val data = weatherViewModel.getCurrentWeather().asFlow()
//
//        assertNotNull(data)
//    }
//}