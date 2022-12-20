/*
 * Copyright (c) 2022. Stephen Muindi
 */

package com.steve_md.joomia.ui.fragments.home


import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.Display.Mode
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.room.Query
import com.steve_md.joomia.R
import com.steve_md.joomia.adapters.ProductsItemAdapter
import com.steve_md.joomia.databinding.FragmentHomeBinding
import com.steve_md.joomia.util.ApiStates
import com.steve_md.joomia.util.CartCounter
import com.steve_md.joomia.util.CartCounter.counter
import com.steve_md.joomia.util.toast
import com.steve_md.joomia.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.observeOn
import timber.log.Timber


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val productsViewModel by viewModels<ProductsViewModel>()
    private val productsItemAdapter by lazy { ProductsItemAdapter() }

    // Shared Preferences
    private lateinit var sharedPreferences: SharedPreferences
    private var sharedIdValue : Int  = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.searchView2.setBackgroundColor(Color.WHITE)
        //binding.searchView2.setBackgroundResource(R.drawable.background_search)

        val root = binding.root

        return root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToProductsObserver()

       // searchFilterFunctionality()

        cartIconNavigateToAddToCartFragment()


        checkCartCounterCartLineItemsQuantity()

    }

    private fun checkCartCounterCartLineItemsQuantity() {
        sharedPreferences = requireActivity().getSharedPreferences("Cart Counter Shared Preferences", Context.MODE_PRIVATE)
        sharedIdValue = sharedPreferences.getInt(counter.toString(), 0)

        if (sharedIdValue == 0) {
            binding.cartBadge.isVisible = false
        } else if (sharedIdValue == 1) {
            binding.cartBadge.text = sharedIdValue.toString()
            binding.cartBadge.isVisible  = true
        }
    }

    private fun cartIconNavigateToAddToCartFragment() {
        binding.cartIconImageView.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addToCartFragment)
        }
    }


    private fun searchFilterFunctionality() {
        binding.searchView2.setOnQueryTextListener(object :SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchFilterProducts(query = toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchFilterProducts(query = toString())
                return true
            }

        })
    }

    private fun searchFilterProducts(query: String) {
        // Custom Sql Query When Searching Products By Filtering Them
        val searchQuery = "%$query%"
        productsViewModel.searchProductsFromLocalDatabase(searchQuery).observe(viewLifecycleOwner) { list ->
            list.let {
                productsItemAdapter.submitList(it)
            }
        }
    }

    private fun subscribeToProductsObserver() {
        productsViewModel.products.observe(viewLifecycleOwner) {
            when(it){
                is ApiStates.Error -> {
                    it.error?.localizedMessage?.toString().let { it1 -> toast(it1!!) }
                    binding.apply {
                        progressBar.isVisible = false
                    }
                }

                is ApiStates.Loading -> {
                    binding.apply {
                        binding.shimmerLayout.startShimmer()
                    }
                }

                is ApiStates.Success -> {
                    binding.apply {
                       // progressBar.isVisible = false
                        binding.shimmerLayout.startShimmer()
                        binding.shimmerLayout.stopShimmer()
                        binding.shimmerLayout.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                    }
                }
                //else -> {}
            }
            productsItemAdapter.submitList(it.data)
            binding.recyclerView.adapter = productsItemAdapter
            Timber.i("Data Fetched successfully")
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

