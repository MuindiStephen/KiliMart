package com.steve_md.joomia.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.steve_md.joomia.data.local.entity.CartEntity
import com.steve_md.joomia.databinding.CartItemRowBinding

class CartAdapter (
    private val onClickListener: OnClickListener
        )
    : ListAdapter<CartEntity, CartAdapter.CartViewHolder>(CartDiffUtil){
    object CartDiffUtil : DiffUtil.ItemCallback<CartEntity>() {
        override fun areItemsTheSame(oldItem: CartEntity, newItem: CartEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CartEntity, newItem: CartEntity): Boolean {
            return oldItem.id == newItem.id
        }

    }

    inner class CartViewHolder(private val binding:CartItemRowBinding) :  RecyclerView.ViewHolder(binding.root) {

        val deleteItemFromCartLine = binding.buttonRemoveCartItem
        @SuppressLint("SetTextI18n")
        fun bind(cartLineItem: CartEntity?) {
            Glide.with(binding.imageView3).load(cartLineItem?.image)
                .centerCrop()
                .fitCenter()
                .into(binding.imageView3)

            binding.textViewProductName.text = cartLineItem?.title
            binding.textView2.text = "$"+cartLineItem?.price.toString()


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            CartItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartLineItem = getItem(position)
        holder.bind(cartLineItem)

        holder.deleteItemFromCartLine.setOnClickListener {
            onClickListener.onClick(cartLineItem)
        }
    }

    class OnClickListener(val clickListener: (cart: CartEntity) -> Unit) {
        fun onClick(cart: CartEntity) = clickListener(cart)
    }

}