package com.steve_md.joomia.network.api

import com.steve_md.joomia.model.Products
import com.steve_md.joomia.model.ProductsItem
import com.steve_md.joomia.util.Constants.END_POINT
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
   @GET("products")
   fun getProducts() : Call<ArrayList<ProductsItem>>
}