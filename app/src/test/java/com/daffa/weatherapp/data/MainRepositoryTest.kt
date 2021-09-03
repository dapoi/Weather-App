package com.daffa.weatherapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.daffa.weatherapp.data.remote.network.ApiConfig
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: MainRepository

    @Before
    fun setUp() {
        repository = MainRepository(ApiConfig.provideApiService())
    }

    @Test
    fun weatherRepository() = runBlocking {
        val data = repository.getWeatherData()
        Assert.assertEquals("Bekasi", data.name)
    }
}