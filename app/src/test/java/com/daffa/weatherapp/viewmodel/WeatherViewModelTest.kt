package com.daffa.weatherapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asFlow
import com.daffa.weatherapp.CoroutineTestRule
import com.daffa.weatherapp.DataDummy
import com.daffa.weatherapp.data.source.WeatherRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@FlowPreview
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    private lateinit var weatherViewModel: WeatherViewModel

    @Mock
    private var weatherRepository = mock(WeatherRepository::class.java)

    @Before
    fun setUp() {
        weatherViewModel = WeatherViewModel(weatherRepository)
    }

    @Test
    fun testGetWeatherData() = coroutineTestRule.testDispatcher.runBlockingTest {
        val data = DataDummy.getWeatherLiveData()
        `when`(weatherRepository.getCurrentWeather()).thenReturn(data.asFlow())

        assertNotNull(weatherViewModel)
    }
}