/*
 * Copyright (c)  Stephen Muindi
 */

package com.steve_md.joomia.ui.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.steve_md.joomia.R

class MobileMoneyModalBottomSheet : BottomSheetDialogFragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflating MobileMoney layout to this fragment
        return inflater.inflate(R.layout.mobilem_money_payment_bottom_sheet_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mpesa = view.findViewById<CardView>(R.id.mpesaCardView)
        val airtel = view.findViewById<CardView>(R.id.airtelMoneyCardView)
        val buttonConfirm = view.findViewById<Button>(R.id.buttonConfirm)

        mpesa.setOnClickListener {
            buttonConfirm.isEnabled = it.isEnabled
        }
        airtel.setOnClickListener {
            buttonConfirm.isEnabled = it.isEnabled
        }
    }
}