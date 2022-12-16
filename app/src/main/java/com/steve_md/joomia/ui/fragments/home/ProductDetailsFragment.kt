package com.steve_md.joomia.ui.fragments.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.steve_md.joomia.databinding.FragmentProductDetailsBinding
import com.steve_md.joomia.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding
    private val viewModel by viewModels<ProductsViewModel>()
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

        }

        val root = binding.root
        return root
    }


}