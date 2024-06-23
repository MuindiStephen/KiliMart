/*
 * Copyright (c)  Stephen Muindi
 */

package com.steve_md.kilimart.ui.fragments.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import co.paystack.android.Paystack
import co.paystack.android.PaystackSdk
import co.paystack.android.PaystackSdk.applicationContext
import co.paystack.android.Transaction
import co.paystack.android.model.Card
import co.paystack.android.model.Charge
import com.google.android.material.textfield.TextInputLayout
import com.steve_md.kilimart.BuildConfig
import com.steve_md.kilimart.R
import com.steve_md.kilimart.databinding.FragmentPaymentBinding
import com.steve_md.kilimart.util.toast
import timber.log.Timber


class PaystackPaymentFragment : Fragment() {

    private lateinit var binding: FragmentPaymentBinding

    private lateinit var mCardNumber: TextInputLayout
    private lateinit var mCardExpiry: TextInputLayout
    private lateinit var mCardCVV: TextInputLayout

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
        initializePaystack()
        initializeFormVariables()

        //val price = 4000


    }
    private fun setUpBinding() {
        binding.imageView4.setOnClickListener { findNavController().navigateUp() }
    }

    private fun initializePaystack() {

    }


    private fun initializeFormVariables() {
        mCardNumber = view?.findViewById(R.id.enterCardNumber)!!
        mCardExpiry = view?.findViewById(R.id.enterCardExpiry)!!
        mCardCVV = view?.findViewById(R.id.enterCVV)!!

        // this is used to add a forward slash (/) between the cards expiry month
        // and year (11/21). After the month is entered, a forward slash is added
        // before the year
        mCardExpiry.editText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?,
                                           start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().length == 2 && !s.toString().contains("/")) {
                    s!!.append("/")
                }
            }

        })

        val buttonPay = view?.findViewById<Button>(R.id.buttonPay)!!
        buttonPay.setOnClickListener { v: View? -> performCharge() }
    }

    private fun performCharge() {
        val cardNumber = mCardNumber.editText!!.text.toString()
        val cardExpiry = mCardExpiry.editText!!.text.toString()
        val cvv = mCardCVV.editText!!.text.toString()

        val cardExpiryArray = cardExpiry.split("/").toTypedArray()
        val expiryMonth = cardExpiryArray[0].toInt()
        val expiryYear = cardExpiryArray[1].toInt()

        val amount = 400

        val card = Card(cardNumber, expiryMonth, expiryYear, cvv)

        val charge = Charge()
        charge.amount = amount
        charge.email = "customer@email.com"
        charge.card = card

        PaystackSdk.chargeCard(requireActivity(), charge, object : Paystack.TransactionCallback {
            override fun onSuccess(transaction: Transaction) {
                parseResponse(transaction.reference )
                Timber.tag("Paystack Payment successful").d("beforeValidate: %s", transaction.reference)

            }

            override fun beforeValidate(transaction: Transaction) {
                Timber.tag("Paystack Payment initial").d("beforeValidate: %s", transaction.reference)
            }

            override fun onError(error: Throwable, transaction: Transaction) {
                Timber.tag("Paystack Payment Failed...").d("onError: %s", error.localizedMessage)

            }
        })
    }

    private fun parseResponse(reference: String?) {
        val message = "Payment Successful - $reference"
        toast(message)
    }

}

