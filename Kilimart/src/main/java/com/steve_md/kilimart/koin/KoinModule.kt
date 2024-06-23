/*
 * Copyright (c)  Stephen Muindi
 */

package com.steve_md.kilimart.koin

import com.steve_md.kilimart.data.remote.api.ApiService
import com.steve_md.kilimart.di.DatabaseModule
import com.steve_md.kilimart.di.NetworkModule
import com.steve_md.kilimart.di.RepositoryModule
import com.steve_md.kilimart.util.Constants.BASE_URL
import com.steve_md.kilimart.viewmodel.ProductsViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { ProductsViewModel(get()) }
}

val retrofitModule = module {
    single { NetworkModule }
    single { DatabaseModule }
    single { RepositoryModule }
}

val repoModule = module {
    single<RepositoryModule> {
        get()
    }
}


val retrofitModule2 = module {

    fun provideHttpClient() =
        OkHttpClient().newBuilder().build()

    fun provideRetrofit(httpClient: OkHttpClient): Retrofit? {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    single { provideRetrofit(get()) }
    single { provideHttpClient() }
    single { provideApiService(get()) }
}

