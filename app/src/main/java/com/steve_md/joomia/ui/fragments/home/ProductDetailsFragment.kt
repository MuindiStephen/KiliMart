/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.steve_md.joomia.ui.fragments.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.steve_md.joomia.R
import com.steve_md.joomia.databinding.FragmentProductDetailsBinding
import com.steve_md.joomia.viewmodel.CartViewModel
import com.steve_md.joomia.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding
    private val viewModel by viewModels<CartViewModel>()


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
            itemAddedToCart()
            navigateToCheckout()
        }


        val root = binding.root
        return root
    }

    private fun navigateToCheckout() {
        findNavController().navigate(R.id.action_productDetailsFragment_to_addToCartFragment)
    }

    private fun itemAddedToCart() {
        Toast.makeText(
            requireContext(),
            "Item has been added to cart",
            Toast.LENGTH_LONG
        ).show()
    }


}