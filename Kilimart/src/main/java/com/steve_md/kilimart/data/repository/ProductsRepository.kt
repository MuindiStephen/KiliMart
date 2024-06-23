/*
 * Copyright (c)  Stephen Muindi
 */



package com.steve_md.kilimart.data.repository

import androidx.room.withTransaction
import com.steve_md.kilimart.data.local.ProductsDatabase
import com.steve_md.kilimart.data.remote.api.ApiService
import com.steve_md.kilimart.model.ProductsItem
import com.steve_md.kilimart.util.networkBoundResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val apiService: ApiService,
    private val productsDatabase: ProductsDatabase
){

    private val productDao = productsDatabase.productsDao()

     fun getAllProducts() = networkBoundResource(

        // Logic to query data from database - [offline cache storage]
         // Querying cached data from the local db
            query = {
                   productDao.getAllProducts()
                },

        // Logic to fetch data from the api
        fetch = {
            delay(2000)
            apiService.getProducts()
        },

        // Logic to save the fetched resultType data from api to the local db
        saveFetchResult = { products ->
          productsDatabase.withTransaction {
              productDao.deleteAllProducts()
              productDao.insertProducts(products)
          }
        },

        // Logic to determine if networking call should be made
        shouldFetch = { products ->
            products.isEmpty()
        }

    )

    // fetching products from the remote api web service


    fun searchDatabase(searchQuery: String) : Flow<List<ProductsItem>> {
        return productDao.searchDatabase(searchQuery)
    }
}