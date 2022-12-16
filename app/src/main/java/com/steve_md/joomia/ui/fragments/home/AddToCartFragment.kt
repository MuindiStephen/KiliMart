package com.steve_md.joomia.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.steve_md.joomia.R
import com.steve_md.joomia.adapters.CartAdapter
import com.steve_md.joomia.databinding.FragmentAddToCartBinding
import com.steve_md.joomia.viewmodel.CartViewModel

class AddToCartFragment : Fragment() {

    private lateinit var binding: FragmentAddToCartBinding
    private val cartAdapter by lazy { CartAdapter() }
    private val cartViewModel by viewModels<CartViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddToCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}