package com.daffa.weatherapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.daffa.weatherapp.CoroutineTestRule
import com.daffa.weatherapp.DataDummy
import com.daffa.weatherapp.data.source.WeatherRepository
import com.daffa.weatherapp.vo.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Mock
    private lateinit var weatherRepository: WeatherRepository

    @Before
    fun setUp() {
        weatherRepository = Mockito.mock(WeatherRepository::class.java)
    }

    @Test
    fun dataRepository() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val weatherDataDummy = DataDummy.getWeatherData()
        val weatherDataFlow = flow {
            emit(Resource.Success(weatherDataDummy))
        }

        assertNotNull(weatherDataDummy)
        assertEquals(weatherDataDummy, weatherDataFlow.first().data)

    }
}