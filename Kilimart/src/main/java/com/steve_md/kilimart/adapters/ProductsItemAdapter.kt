/*
 * Copyright (c)  Stephen Muindi
 */

package com.steve_md.kilimart.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.steve_md.kilimart.databinding.ProductListItemBinding
import com.steve_md.kilimart.model.ProductsItem
import com.steve_md.kilimart.ui.fragments.home.HomeFragmentDirections

class ProductsItemAdapter : ListAdapter<ProductsItem, ProductsItemAdapter.MyViewHolder>(MyDiffUtil) {
    object MyDiffUtil : DiffUtil.ItemCallback<ProductsItem>(){
        override fun areItemsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean {
            return oldItem.id == newItem.id
        }

    }


    inner class MyViewHolder(private val binding: ProductListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(product: ProductsItem?) {
           binding.productPrice.text = "$"+ product?.price.toString()
            binding.productTitle.text = product?.title
            binding.productCategory.text = product?.category
            Glide.with(binding.productImageBox)
                .load(product?.image)
                .centerCrop()
                .into(binding.productImageBox)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ProductListItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        )
    }


    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val product = getItem(position)
        holder.bind(product)
        holder.itemView.setOnClickListener {
          val directions = HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(product)
            it.findNavController().navigate(directions)
        }
    }



}