package com.daffa.weatherapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.daffa.weatherapp.R
import com.daffa.weatherapp.data.source.local.model.WeatherEntity
import com.daffa.weatherapp.databinding.ActivityMainBinding
import com.daffa.weatherapp.viewmodel.ViewModelFactory
import com.daffa.weatherapp.viewmodel.WeatherViewModel
import com.daffa.weatherapp.vo.Resource
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        swipeRefresh()
        weatherResult()
    }

    private fun swipeRefresh() {
        binding.refreshSwipe.setOnRefreshListener {
            weatherResult()
            Handler(Looper.getMainLooper()).postDelayed({
                weatherResult()
                binding.refreshSwipe.isRefreshing = false
            }, 3000)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun weatherResult() {
        val factory = ViewModelFactory.getInstance(this)
        weatherViewModel = ViewModelProvider(this, factory)[WeatherViewModel::class.java]
        weatherViewModel.getCurrentWeather().observe(this, {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> progressBar(true)
                    is Resource.Success -> {
                        progressBar(false)
                        resultData(it)
                    }
                    is Resource.Error -> {
                        progressBar(false)
                        Snackbar.make(
                            binding.parentLayout,
                            "Upps failed to load data, swipe up to refresh",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
        })
        updateTime()
    }

    @SuppressLint("SetTextI18n")
    private fun resultData(result: Resource.Success<WeatherEntity>) {
        binding.apply {
            temperature.text = String.format(
                getString(R.string.temp),
                (result.data?.temp?.div(10))?.toInt().toString()
            )
            address.text = result.data?.name
            statusWeather.text = result.data?.weatherInfo
            tvWind.text = result.data?.speed.toString()
            tvSunrise.text = (result.data?.sunrise?.div(10000000)).toString()
            tvSunset.text = (result.data?.sunset?.div(10000000)).toString()
            tvPressure.text = result.data?.pressure.toString()
            tvHumidity.text = result.data?.humidity.toString()
            tvInfo.text = "Daffa"
        }
    }

    private fun progressBar(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun updateTime() {
        val calendar = Calendar.getInstance()
        val simpleFormat = SimpleDateFormat("EEEE, d MMM yyyy HH:mm")
        val date = simpleFormat.format(calendar.time).toString()
        binding.timeUpdate.text = date
    }
}