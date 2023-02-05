package com.steve_md.joomia

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class HiltApp : Application() {
    override fun onCreate() {
        super.onCreate()
        getTimber()
    }

    private fun getTimber() {
        Timber.plant(Timber.DebugTree())
    }
}