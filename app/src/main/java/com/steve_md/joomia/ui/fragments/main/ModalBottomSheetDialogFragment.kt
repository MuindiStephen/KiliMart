
/*
 * Copyright (c)  Stephen Muindi
 */

package com.steve_md.joomia.ui.fragments.main

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.steve_md.joomia.R

class ModalBottomSheetDialogFragment : BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflating ModalSheet Layout to this fragment
        return inflater.inflate(R.layout.layout_modal_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fun Fragment.setTransparentBackground() {
            val bottomSheet = (requireView().parent as View)
            bottomSheet.apply {
                backgroundTintMode = PorterDuff.Mode.CLEAR
                backgroundTintList = ColorStateList.valueOf(Color.TRANSPARENT)
                setBackgroundColor(Color.TRANSPARENT)
            }
        }


        val backExplore  = view.findViewById<Button>(R.id.btnBackToExplore)
        val btnContinue = view.findViewById<Button>(R.id.btnContinue)
        backExplore?.setOnClickListener { findNavController().navigate(R.id.action_modalBottomSheetDialogFragment_to_homeFragment) }
        btnContinue?.setOnClickListener { findNavController().navigate(R.id.action_modalBottomSheetDialogFragment_to_addToCartFragment) }

        setUpAnimation()

    }

    private fun setUpAnimation() {
        val lottieModalBottomSheet = view?.findViewById<LottieAnimationView>(R.id.lottieModalBottomSheet)
        lottieModalBottomSheet?.setAnimation(R.raw._add_to_cart)
        lottieModalBottomSheet?.repeatCount = LottieDrawable.INFINITE
        lottieModalBottomSheet?.playAnimation()
    }

}



