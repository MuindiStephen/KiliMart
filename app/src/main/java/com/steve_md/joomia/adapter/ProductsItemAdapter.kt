package com.steve_md.joomia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.steve_md.joomia.databinding.ProductListItemBinding
import com.steve_md.joomia.model.ProductsItem

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
        fun bind(product: ProductsItem?) {
           binding.productPrice.text = product?.price.toString()
            binding.productTitle.text = product?.title
            Glide.with(binding.productImageBox)
                .load(product?.image)
                .into(binding.productImageBox)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ProductListItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val product = getItem(position)
        holder.bind(product)
    }



}