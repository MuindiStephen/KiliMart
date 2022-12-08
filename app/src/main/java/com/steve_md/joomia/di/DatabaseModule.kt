package com.steve_md.joomia.di

import android.app.Application
import androidx.room.Room
import com.steve_md.joomia.data.local.ProductsDao
import com.steve_md.joomia.data.local.ProductsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesRoomDatabase(context: Application) : ProductsDatabase {
         return Room.databaseBuilder(context, ProductsDatabase::class.java, "products.db")
             .allowMainThreadQueries()  // without blocking the main thread
             .build()
    }

    @Provides
    @Singleton
    fun providesProductsDao(productsDatabase: ProductsDatabase) : ProductsDao {
        return productsDatabase.productsDao()
    }

}
