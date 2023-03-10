/*
 * Copyright (c)  Stephen Muindi
 */

package com.steve_md.joomia.ui.fragments.main

import android.os.Bundle
<<<<<<< HEAD
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.steve_md.joomia.databinding.FragmentPaypalBinding

class PaypalFragment : Fragment() {

    private lateinit var binding: FragmentPaypalBinding

=======
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.steve_md.joomia.R


class PaypalFragment : Fragment() {

>>>>>>> 26-paypal-payments-integration
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
<<<<<<< HEAD
        binding = FragmentPaypalBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpBinding()
    }

    private fun setUpBinding() {
        TODO("Not yet implemented")
    }
=======
        return inflater.inflate(R.layout.fragment_paypal, container, false)
    }

>>>>>>> 26-paypal-payments-integration
}