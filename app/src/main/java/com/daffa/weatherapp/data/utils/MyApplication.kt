package com.daffa.weatherapp.data.utils

import android.app.Application
import com.daffa.weatherapp.BuildConfig
import timber.log.Timber

open class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}