/*
 * Copyright (c)  Stephen Muindi
 */

package com.steve_md.kilimart

import android.app.Application
import com.steve_md.kilimart.koin.repoModule
import com.steve_md.kilimart.koin.retrofitModule
import com.steve_md.kilimart.koin.retrofitModule2
import com.steve_md.kilimart.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class KoinApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeKoin()
    }

    private fun initializeKoin() {
        startKoin {
            androidContext(this@KoinApp)
            androidLogger(Level.DEBUG)
            modules(
                listOf(
                    retrofitModule,
                    viewModelModule,
                    retrofitModule2,
                    repoModule
                )
            )
        }
    }
}