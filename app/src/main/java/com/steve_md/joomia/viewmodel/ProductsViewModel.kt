package com.steve_md.joomia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.steve_md.joomia.data.repository.ProductsRepository
import com.steve_md.joomia.model.ProductsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    val products = productsRepository.getAllProducts().asLiveData()

    fun searchProductsFromLocalDatabase(searchQuery: String) : Flow<List<ProductsItem>> {
        return productsRepository.searchDatabase(searchQuery)
    }
}

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

//    val products : Job = viewModelScope.launch{
//        repository.getAllProducts().asLiveData()
//    }



    /* same as this
    private fun getAllProducts()  = viewModelScope.launch {
             repository.getAllProducts().asLiveData()
             //also { _products?.value = it }
         }
   */





