package com.steve_md.joomia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.steve_md.joomia.data.local.ProductsDatabase
import com.steve_md.joomia.data.remote.api.FakeJoomiaApp.retrofitApi
import com.steve_md.joomia.data.repository.ProductsRepository
import com.steve_md.joomia.model.ProductsItem
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel(){
  private val repository: ProductsRepository = ProductsRepository(apiService = retrofitApi, productsDatabase = ProductsDatabase.productsDao())

      private var _products:MutableLiveData<List<ProductsItem>?>? =
          MutableLiveData<List<ProductsItem>?>()
      val products: MutableLiveData<List<ProductsItem>?>?
         get() = _products

      fun getAllProducts()  = viewModelScope.launch{
         _products?.value = repository.getAllProducts()
    }

}