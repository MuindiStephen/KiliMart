package com.steve_md.joomia.viewmodel

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.steve_md.joomia.ui.SplashScreenFragment

class SplashViewModel : ViewModel()  {
    // LiveData
    private val value: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val _liveData: LiveData<Boolean> = value

    fun setValue() {
        Handler().postDelayed({
            value.value = true
        }, 3500)
    }


}