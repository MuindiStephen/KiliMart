package com.steve_md.joomia.adapters

import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.steve_md.joomia.data.local.entity.CartEntity

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

    inner class CartViewHolder(itemView: View) :  RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}