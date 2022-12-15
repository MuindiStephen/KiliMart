package com.steve_md.joomia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.steve_md.joomia.data.local.entity.CartEntity
import com.steve_md.joomia.data.local.mappers.toCartEntity
import com.steve_md.joomia.data.repository.CartRepository
import com.steve_md.joomia.model.ProductsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {

    val cartLineItems = cartRepository.getCartLineItems()

    fun insertItemToCartLine(productItem:ProductsItem) = viewModelScope.launch {
        cartRepository.insertProductToCartLineItem(productItem.toCartEntity())  // use of mapper
    }

    fun deleteAllItemsFromCartLine() = viewModelScope.launch {
        cartRepository.deleteAllCartItems()
    }

    fun removeOnlyOneItemFromCartLine(cartEntity: CartEntity) = viewModelScope.launch {
        cartRepository.deleteAnItemFromCartLine(cartEntity)
    }

}