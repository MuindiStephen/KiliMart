package com.steve_md.joomia.ui.fragments.splash

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.steve_md.joomia.R
import com.steve_md.joomia.databinding.FragmentSplashScreenBinding
import com.steve_md.joomia.util.ApiStates
import com.steve_md.joomia.viewmodel.SplashViewModel

class SplashScreenFragment : Fragment() {

    // binding
    private lateinit var binding: FragmentSplashScreenBinding
    // private val viewModel : SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

       binding = FragmentSplashScreenBinding.inflate(inflater, container, false)

//        viewModel._liveData.observe(viewLifecycleOwner) {
//            findNavController().navigate(R.id.action_splashScreenFragment_to_mainActivity)
//        }
//        viewModel.setValue()


        Handler().postDelayed(
            {
            findNavController().navigate(R.id.action_splashScreenFragment_to_homeFragment)
        }, 3000
        )

        return binding.root
    }

}