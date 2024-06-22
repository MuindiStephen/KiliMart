/*
 * Copyright (c)  Stephen Muindi
 */

package com.steve_md.kilimart.ui.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.steve_md.kilimart.databinding.FragmentScanProductBinding


class ScanProductFragment : Fragment() {

    private lateinit var binding: FragmentScanProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentScanProductBinding.inflate(inflater,container, false)
        return binding.root
    }

}