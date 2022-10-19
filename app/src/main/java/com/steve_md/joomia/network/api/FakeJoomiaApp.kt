package com.steve_md.joomia.network.api

import com.steve_md.joomia.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object FakeJoomiaApp {
    private var retrofit = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitApi by lazy { retrofit.create(ApiService::class.java) }
}