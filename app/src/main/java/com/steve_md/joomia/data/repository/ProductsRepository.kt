package com.steve_md.joomia.data.repository

import com.steve_md.joomia.data.remote.api.ApiService

class ProductsRepository (
    private val apiService: ApiService
        ){

    suspend fun getAllProducts() {
        apiService.getProducts()
    }
}