/*
 * Copyright (c) 2022. Stephen Muindi
 */

package com.steve_md.joomia.ui.fragments.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.steve_md.joomia.R
import com.steve_md.joomia.adapters.ProductsItemAdapter
import com.steve_md.joomia.databinding.FragmentHomeBinding
import com.steve_md.joomia.util.CartCounter.counter
import com.steve_md.joomia.util.hideKeyboard
import com.steve_md.joomia.util.toast
import com.steve_md.joomia.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val productsViewModel by viewModels<ProductsViewModel>()
    private val productsItemAdapter by lazy { ProductsItemAdapter() }

    // Shared Preferences
    private lateinit var sharedPreferences: SharedPreferences
    private var sharedIdValue: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Search
        binding.searchProduct.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // Hide keyboard
                hideKeyboard()

                val searchText = binding.searchView.editText?.text.toString().trim()

                if (searchText.isEmpty()) {
                    toast(message = "Please input some text in order to search")
                    false
                }
                productsViewModel.getProducts(searchQuery = searchText)
                true
            } else {
                false
            }
        }

        binding.searchView.setEndIconOnClickListener {
            hideKeyboard()

            if (binding.searchView.editText?.text.isNullOrEmpty()) {
                return@setEndIconOnClickListener
            }

            binding.searchView.editText?.setText("")
            productsViewModel.getProducts()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToProductsObserver()
        cartIconNavigateToAddToCartFragment()
        checkCartCounterCartLineItemsQuantity()
    }

    private fun checkCartCounterCartLineItemsQuantity() {
        sharedPreferences =
            requireActivity().getSharedPreferences(
                "Cart Counter Shared Preferences",
                Context.MODE_PRIVATE
            )
        sharedIdValue = sharedPreferences.getInt(counter.toString(), 0)

        if (sharedIdValue == 0) {
            binding.cartBadge.isVisible = false
        } else if (sharedIdValue == 1) {
            binding.cartBadge.text = sharedIdValue.toString()
            binding.cartBadge.isVisible = true
        }
    }

    private fun cartIconNavigateToAddToCartFragment() {
        binding.cartIconImageView.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addToCartFragment)
        }
    }

    private fun subscribeToProductsObserver() {
        lifecycleScope.launch {
            productsViewModel.products.collect { productState ->
                Timber.e("State state: $productState")

                // Loading state
                if (productState.isLoading) {
                    binding.shimmerLayout.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    binding.shimmerLayout.startShimmer()
                    binding.errorOrEmptyStateTextView.visibility = View.GONE
                }

                // There is an error
                if (productState.error != null && !productState.isLoading) {
                    toast(message = productState.error)
                    binding.shimmerLayout.stopShimmer()
                    binding.errorOrEmptyStateTextView.visibility = View.VISIBLE
                    binding.errorOrEmptyStateTextView.text = productState.error
                }

                // Products have been loaded
                if (productState.products.isNotEmpty() && !productState.isLoading) {
                    binding.apply {
                        binding.shimmerLayout.stopShimmer()
                        binding.shimmerLayout.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.errorOrEmptyStateTextView.visibility = View.GONE
                    }

                    productsItemAdapter.submitList(productState.products)
                    binding.recyclerView.adapter = productsItemAdapter
                    Timber.i("Data Fetched successfully: ${productState.products}")
                }

                // No products found
                if (productState.products.isEmpty() && !productState.isLoading) {
                    binding.apply {
                        binding.shimmerLayout.stopShimmer()
                        binding.shimmerLayout.visibility = View.GONE
                        binding.recyclerView.visibility = View.GONE
                    }

                    binding.errorOrEmptyStateTextView.visibility = View.VISIBLE
                    binding.errorOrEmptyStateTextView.text = "Nothing found"
                }
            }
        }
    }

    override fun onResume() {
        binding.shimmerLayout.startShimmer()
        super.onResume()
    }

    override fun onPause() {
        binding.shimmerLayout.stopShimmer()
        super.onPause()
    }
}
