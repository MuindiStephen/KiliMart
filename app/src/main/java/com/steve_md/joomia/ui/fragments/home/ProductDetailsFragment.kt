/*
 * Copyright (c)  Stephen Muindi
 */

package com.steve_md.joomia.ui.fragments.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.steve_md.joomia.R
import com.steve_md.joomia.databinding.FragmentProductDetailsBinding
import com.steve_md.joomia.ui.fragments.main.ModalBottomSheetDialogFragment
import com.steve_md.joomia.util.CartCounter
import com.steve_md.joomia.util.CartCounter.counter
import com.steve_md.joomia.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding
    private val viewModel by viewModels<CartViewModel>()
    private lateinit var sharedPreferences: SharedPreferences
    private var sharedIdValue : Int  = 0


    private val args:ProductDetailsFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)

        val productsItem = args.productitem

        binding.apply {
            textViewProductName.text = productsItem.title
            textViewProductPrice.text = "$" + productsItem.price.toString()
            textViewProductDescription.text = productsItem.description
            Glide.with(requireContext())
                .load(productsItem.image)
                .into(imageViewProductImage)

        }

        binding.buttonAddToCart.setOnClickListener {
            viewModel.insertItemToCartLine(productsItem)

            val cartBadge: TextView? = view?.findViewById(R.id.cartBadge)
            val cartBadgeValue = 0
            cartBadge?.id = cartBadgeValue
            cartBadge?.text = (cartBadgeValue + 1).toString()

            //Pop up ModalBottomSheet
            initiateModalBottomSheet()

            /*
            * navigateToCheckout()
            * increaseCounterItemIcon()
           */
        }


        return binding.root
    }

    // Calling ModalBottomSheetClass in MainActivity
    private fun initiateModalBottomSheet() {
        ModalBottomSheetDialogFragment().apply {

        }
    }

    private fun increaseCounterItemIcon() {
        sharedPreferences = requireActivity().getSharedPreferences("Cart Counter Shared Preferences", Context.MODE_PRIVATE)
        sharedIdValue = sharedPreferences.getInt(CartCounter.counter.toString(), 0)

        sharedPreferences.edit()
            .putInt(counter.toString(),sharedPreferences.getInt(counter.toString(),0)+1)
            .apply()

        val sharedIdValue = sharedPreferences.getInt(counter.toString(),0)
        val cartBadge:TextView? = view?.findViewById(R.id.cartBadge)
        cartBadge?.text = sharedIdValue.toString()
        cartBadge?.isVisible = true

        if (sharedIdValue == 0) {
            cartBadge?.isVisible = false
        } else if (sharedIdValue >= 1) {
            cartBadge?.isVisible  = true
        }
    }


        /*      private fun navigateToCheckout() {
                     findNavController().navigate(R.id.action_productDetailsFragment_to_addToCartFragment)
                }

                 private fun itemAddedToCart() { }

     */


}