/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.steve_md.joomia.ui.fragments.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.steve_md.joomia.R
import com.steve_md.joomia.adapters.CartAdapter
import com.steve_md.joomia.data.local.CartDao
import com.steve_md.joomia.data.local.entity.CartEntity
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
        val inflate = FragmentAddToCartBinding.inflate(inflater, container, false)
        binding = inflate
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    
    // Getting all cart items
        cartViewModel.cartLineItems.observe(viewLifecycleOwner) { cartItem ->
            cartAdapter.submitList(cartItem)
            binding.cartLineItemsRecyclerView.adapter = cartAdapter
        }


        binding.buttonCheckout.setOnClickListener {
            findNavController().navigate(R.id.action_addToCartFragment_to_paymentFragment)
        }

    }


}