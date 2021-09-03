package com.daffa.weatherapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.daffa.weatherapp.data.MainRepository
import com.daffa.weatherapp.data.remote.network.ApiConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mainViewModel: MainViewModel
    private lateinit var repository: MainRepository
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        mainViewModel = MainViewModel()
        repository = MainRepository(ApiConfig.provideApiService())
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetWeatherData() = runBlockingTest {
        val data = mainViewModel.weather.value
        if (data != null) {
            assertEquals(data.name, "Bekasi")
            assertNotNull(data.wind.speed)
            assertNotNull(data.sys.sunrise)
            assertNotNull(data.sys.sunset)
            assertNotNull(data.main.pressure)
            assertNotNull(data.main.humidity)
            assertNotNull(data.weather[0].main)
        }
    }

    @Test
    fun weatherRepository() = runBlocking {
        val data = repository.getWeatherData()
        assertEquals("Bekasi", data.name)
    }
}