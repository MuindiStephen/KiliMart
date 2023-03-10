/*
 * Copyright (c)  Stephen Muindi
 */

package com.steve_md.joomia.ui.fragments.main

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.paypal.android.sdk.payments.*
import com.steve_md.joomia.databinding.FragmentPaypalBinding
import com.steve_md.joomia.util.PaypalConfig.PAYPAL_CLIENT_ID
import com.steve_md.joomia.util.toast
import org.json.JSONException
import timber.log.Timber
import java.math.BigDecimal

class PaypalFragment : Fragment() {

    private lateinit var binding: FragmentPaypalBinding


    private var amount:String = ""


    // User for testing in payment screen.
    // User > sb-54olb5257515@personal.example.com
    // Password > Password01



    companion object {
        private val PAYPAL_REQUEST_CODE = 711
        private const val CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_SANDBOX

        private val config = PayPalConfiguration()
            .environment(CONFIG_ENVIRONMENT)
            .clientId(PAYPAL_CLIENT_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPaypalBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpBinding()

        val intent = Intent(context, PayPalService::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
        requireActivity().startService(intent)
    }

    private fun setUpBinding() {
        binding.imageView4.setOnClickListener { findNavController().navigateUp() }
        binding.buttonPaypal.setOnClickListener { processPayment() }
    }

    private fun processPayment() {
        amount = binding.enterAmountPaypal.text.toString()
        val paypalPayment = PayPalPayment(
            BigDecimal(amount), "USD", "Pay for cart Items",
            PayPalPayment.PAYMENT_INTENT_SALE)

        val intent = Intent(context, PaymentActivity::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,paypalPayment)
        startActivityForResult(intent, PAYPAL_REQUEST_CODE)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PAYPAL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                val confirmation: PaymentConfirmation? = data!!.getParcelableExtra<PaymentConfirmation>(PaymentActivity.EXTRA_RESULT_CONFIRMATION)
                if (confirmation != null) {
                    try {
                        val paymentDetails = confirmation.toJSONObject().toString(4)
                        Timber.i(paymentDetails)

                    } catch (e: JSONException) {
                        e.printStackTrace()
                        Timber.e("an extremely unlikely failure occurred")
                    }
                }else if(resultCode == Activity.RESULT_CANCELED) {
                    toast("CANCEL")
                }
            }else if(resultCode ==PaymentActivity.RESULT_EXTRAS_INVALID){
                toast("INVALID")

            }
        }
    }





}