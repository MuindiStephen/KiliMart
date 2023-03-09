/*
 * Copyright (c)  Stephen Muindi
 */

package com.steve_md.joomia.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.steve_md.joomia.R
import com.steve_md.joomia.databinding.FragmentPaymentBinding

class PaymentFragment : Fragment() {

    private lateinit var binding: FragmentPaymentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPaymentBinding.inflate(layoutInflater, container, false)

        val root = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        Paystack API KEY
        PSTK_PUBLIC_KEY="pk_test_359cdc41842728fd136567b62203efb25476e08d"
        */

        // Mobile Money
        binding.mobileMoneyRl.setOnClickListener {
            binding.radioButtonMobileMoney.isChecked = true
            if (binding.radioButtonMobileMoney.isChecked) {
                findNavController().navigate(R.id.action_paymentFragment_to_mobileMoneyModalBottomSheet)
            }
            else {
                return@setOnClickListener
            }
        }

        binding.paystackRl.setOnClickListener {
            binding.radioButtonPaystack.isChecked = true
            if (binding.radioButtonPaystack.isChecked) {

            }
        }

    }



}