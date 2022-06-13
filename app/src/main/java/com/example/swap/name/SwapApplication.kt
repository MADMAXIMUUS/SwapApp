package com.example.swap.name

import android.app.Application
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SwapApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}