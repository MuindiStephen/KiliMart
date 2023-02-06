/*
 * Copyright (c)  Stephen Muindi
 */

package com.steve_md.joomia.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel()  {
    // LiveData
    private val value: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val _value: LiveData<Boolean>
    get() = value

    fun setValue() {
        Handler(Looper.getMainLooper()).postDelayed({
            value.value = true
        }, 3500)
    }


}