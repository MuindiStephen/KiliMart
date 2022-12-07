package com.steve_md.joomia.viewmodel

import androidx.lifecycle.*
import androidx.room.Query
import com.steve_md.joomia.data.local.ProductsDatabase
import com.steve_md.joomia.data.remote.api.FakeJoomiaApp.retrofitApi
import com.steve_md.joomia.data.repository.ProductsRepository
import com.steve_md.joomia.model.ProductsItem
import com.steve_md.joomia.util.ApiStates
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel(){
  private val repository : ProductsRepository = TODO()

  /*
   ProductsRepository(
      apiService = retrofitApi,
      productsDatabase = ProductsDatabase.productsDao()
  )
  */
//
    /*
    private var _products = MutableStateFlow<ApiStates<List<ProductsItem>>?>(null)
       var products: StateFlow<ApiStates<List<ProductsItem>>?> get() = _products

    init {
        getAllProducts()
    }
    */

    val products = viewModelScope.launch{
        repository.getAllProducts().asLiveData()
    }

   /* same as this
   private fun getAllProducts()  = viewModelScope.launch {
            repository.getAllProducts().asLiveData()
            //also { _products?.value = it }
        }
  */


     fun searchProductsFromLocalDatabase(searchQuery: String) : Flow<List<ProductsItem>> {
         return repository.searchDatabase(searchQuery)
     }

    }



