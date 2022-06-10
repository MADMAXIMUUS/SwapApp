package com.example.swap.name

import android.app.Application
import timber.log.Timber


class SwapApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}