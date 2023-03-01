/*
 * Copyright (c)  Stephen Muindi
 */

package com.steve_md.joomia.ui.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.steve_md.joomia.databinding.FragmentStepViewBinding


class StepView : Fragment() {

    private lateinit var bindingStepView: FragmentStepViewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingStepView = FragmentStepViewBinding.inflate(
            inflater,container,false
        )

       return bindingStepView.root
    }


}