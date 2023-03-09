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
import com.steve_md.joomia.databinding.FragmentPaymentBinding


class PaystackPaymentFragment : Fragment() {

    private lateinit var binding: FragmentPaymentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPaymentBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         setUpBinding()

    }
    private fun setUpBinding() {
        binding.imageView4.setOnClickListener { findNavController().navigateUp() }
    }

}