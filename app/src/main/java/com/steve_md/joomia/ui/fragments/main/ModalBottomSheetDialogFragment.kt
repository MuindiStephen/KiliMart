
/*
 * Copyright (c)  Stephen Muindi
 */

package com.steve_md.joomia.ui.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.steve_md.joomia.R

class ModalBottomSheetDialogFragment : BottomSheetDialogFragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflating ModalSheet Layout to this fragment
        return inflater.inflate(R.layout.layout_modal_bottom_sheet,container,false)
    }

   /* Since We need to Detach and also re-attach the
   *ModalBottomSheetFragment on the Fragment's Activity at the same time
   */
    @Deprecated("Deprecated in Java", ReplaceWith(
        "super.onActivityCreated(savedInstanceState)",
        "com.google.android.material.bottomsheet.BottomSheetDialogFragment"
    )
    )
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

       val backExplore  = view?.findViewById<Button>(R.id.btnBackToExplore)
       val btnContinue = view?.findViewById<Button>(R.id.btnContinue)

       backExplore?.setOnClickListener { findNavController().navigate(R.id.action_productDetailsFragment_to_homeFragment) }
       btnContinue?.setOnClickListener { findNavController().navigate(R.id.action_productDetailsFragment_to_addToCartFragment) }
    }

}