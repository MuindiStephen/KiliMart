/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.steve_md.joomia.ui.fragments.home


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.steve_md.joomia.R
import com.steve_md.joomia.adapters.ProductsItemAdapter
import com.steve_md.joomia.databinding.FragmentHomeBinding
import com.steve_md.joomia.util.ApiStates
import com.steve_md.joomia.util.toast
import com.steve_md.joomia.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val productsViewModel by viewModels<ProductsViewModel>()
    private val productsItemAdapter by lazy { ProductsItemAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.searchView2.setBackgroundColor(Color.WHITE)
        //binding.searchView2.setBackgroundResource(R.drawable.background_search)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productsViewModel.products.observe(viewLifecycleOwner) {
            when(it){
                is ApiStates.Error -> {
                    it.error?.localizedMessage?.toString().let { it1 -> toast(it1!!) }
                    //toast(it.error?.localizedMessage.toString())
                    binding.apply {
                        progressBar.isVisible = false
                    }
                }

                is ApiStates.Loading -> {
                    binding.apply {
                        progressBar.isVisible = true
                    }
                }

                is ApiStates.Success -> {
                    binding.apply {
                        progressBar.isVisible = false
                    }
                }
                //else -> {}
            }
            productsItemAdapter.submitList(it.data)
            binding.recyclerView.adapter = productsItemAdapter
            Timber.i("Data Fetched successfully")
        }


    }

}

