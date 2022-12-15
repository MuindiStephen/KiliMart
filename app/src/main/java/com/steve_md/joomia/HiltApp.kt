package com.steve_md.joomia

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class HiltApp : Application() {
    // Timber logs
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}