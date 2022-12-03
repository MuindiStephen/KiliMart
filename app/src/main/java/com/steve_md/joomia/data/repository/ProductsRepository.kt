package com.steve_md.joomia.data.repository

import com.steve_md.joomia.data.local.ProductsDatabase
import com.steve_md.joomia.data.remote.api.ApiService
import com.steve_md.joomia.model.ProductsItem
import java.util.concurrent.Flow

class ProductsRepository(
    private val apiService: ApiService,
    private val productsDatabase: ProductsDatabase
){

     private val productsDao = productsDatabase.productsDao()

    suspend fun getAllProducts() {


        // fetching products from the remote api web service
        apiService.getProducts()

        // Querying cached data from the local db
        productsDao.getAllProducts()
    }

    /*
    fun searchDatabase(searchQuery: String) : Flow<List<ProductsItem>> {
        return productsDao.searchDatabase(searchQuery)
    }*/
}