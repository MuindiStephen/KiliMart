package com.steve_md.joomia.ui.fragments.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.Lottie
import com.airbnb.lottie.LottieDrawable
import com.steve_md.joomia.R
import com.steve_md.joomia.databinding.FragmentSplashScreenBinding
import com.steve_md.joomia.util.ApiStates
import com.steve_md.joomia.viewmodel.SplashViewModel

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {

    // binding
    private lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

       binding = FragmentSplashScreenBinding.inflate(inflater, container, false)

        Handler(Looper.getMainLooper()).postDelayed(
            {
            findNavController().navigate(R.id.action_splashScreenFragment_to_homeFragment)
        }, 3000)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAnimation()
    }

    private fun setUpAnimation() {
        binding.apply {
            splashScreenLottieAnimation.setAnimation(R.raw.animation)
            splashScreenLottieAnimation.repeatCount = LottieDrawable.INFINITE
            splashScreenLottieAnimation.playAnimation()
        }
    }

}