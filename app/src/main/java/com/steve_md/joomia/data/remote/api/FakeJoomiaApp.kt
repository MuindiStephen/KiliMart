package com.steve_md.joomia.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FakeJoomiaApp {
    private var retrofit = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitApi by lazy { retrofit.create(ApiService::class.java) }
}