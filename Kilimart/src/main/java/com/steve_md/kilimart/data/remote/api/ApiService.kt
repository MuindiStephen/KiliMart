/*
 * Copyright (c)  Stephen Muindi
 */

package com.steve_md.kilimart.data.remote.api

import com.steve_md.kilimart.model.ProductsItem
import retrofit2.http.GET

interface ApiService {

   // fetch resource from the network
   // This function returns the requestType
   @GET("products")
   suspend fun getProducts() : ArrayList<ProductsItem>

}