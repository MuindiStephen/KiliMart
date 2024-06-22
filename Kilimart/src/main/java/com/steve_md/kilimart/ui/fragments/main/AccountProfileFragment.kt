/*
 * Copyright (c)  Stephen Muindi
 */

package com.steve_md.kilimart.ui.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.steve_md.kilimart.databinding.FragmentAccountProfileBinding

class AccountProfileFragment : Fragment() {

    private lateinit var binding : FragmentAccountProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountProfileBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpBinding()
    }

    private fun setUpBinding() {
        binding.btnLogOut.setOnClickListener { activity?.finishAffinity() }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding
    }


}