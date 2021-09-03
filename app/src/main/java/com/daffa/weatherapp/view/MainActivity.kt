package com.daffa.weatherapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.daffa.weatherapp.R
import com.daffa.weatherapp.databinding.ActivityMainBinding
import com.daffa.weatherapp.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private var reload = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        weatherResult()

        binding.refreshSwipe.setOnRefreshListener {
            weatherResult()
            reload.schedule(300L) {
                binding.refreshSwipe.isRefreshing = false
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun weatherResult() {

        viewModel.weather.observe(this, {
            binding.apply {
                temperature.text = String.format(
                    getString(R.string.temp),
                    (it.main.temp / 10).toInt().toString()
                )
                maxTemp.text =
                    String.format(getString(R.string.temp), it.main.tempMax.toInt().toString())
                minTemp.text =
                    String.format(getString(R.string.temp), it.main.tempMin.toInt().toString())
                address.text = it.name
                statusWeather.text = it.weather[0].main
                tvWind.text = it.wind.speed.toString()
                tvSunrise.text = (it.sys.sunrise / 10000000).toString()
                tvSunset.text = (it.sys.sunset / 10000000).toString()
                tvPressure.text = it.main.pressure.toString()
                tvHumidity.text = it.main.humidity.toString()
                tvInfo.text = "Daffa"
            }
            updateTime()
        })
    }

    @SuppressLint("SimpleDateFormat")
    private fun updateTime() {
        val calendar = Calendar.getInstance()
        val simpleFormat = SimpleDateFormat("EEEE, dd LLLL yyyy HH:mmaaa z")
        val date = simpleFormat.format(calendar.time).toString()
        binding.timeUpdate.text = date
    }
}