package com.steve_md.joomia.di

import com.steve_md.joomia.data.local.ProductsDatabase
import com.steve_md.joomia.data.remote.api.ApiService
import com.steve_md.joomia.data.repository.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesRepository(apiService: ApiService,
                           productsDatabase: ProductsDatabase
    ) : ProductsRepository {
        return ProductsRepository(apiService, productsDatabase)
    }
}