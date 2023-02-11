/*
 * Copyright (c)  Stephen Muindi
 */

package com.steve_md.joomia.ui.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.steve_md.joomia.R
import com.steve_md.joomia.databinding.FragmentPaymentSuccessfulBinding


class PaymentSuccessfulFragment : Fragment() {

    private lateinit var binding : FragmentPaymentSuccessfulBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentSuccessfulBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpBinding()
        setUpAnimation()
    }

    private fun setUpAnimation() {
       binding.apply {
           paymentSuccessLottieAnimationView.setAnimation(R.raw.success_popup)
           paymentSuccessLottieAnimationView.repeatCount = 0 //Never Repeat
           paymentSuccessLottieAnimationView.playAnimation()
       }
    }

    private fun setUpBinding() {
        binding.btnGoHome.setOnClickListener {
            findNavController().navigate(R.id.action_paymentSuccessfulFragment_to_homeFragment)
        }
    }
}