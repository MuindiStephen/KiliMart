package com.steve_md.kilimart.di

import android.app.Application
import androidx.room.Room
import com.steve_md.kilimart.data.local.ProductsDao
import com.steve_md.kilimart.data.local.ProductsDatabase
import com.steve_md.kilimart.data.repository.CartRepository
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
             .fallbackToDestructiveMigration() //  Want database to not be cleared when upgrading versions from 1_2
            // .addMigrations()
             .build()
    }

    @Provides
    @Singleton
    fun providesProductsDao(productsDatabase: ProductsDatabase) : ProductsDao {
        return productsDatabase.productsDao()
    }


    @Provides
    @Singleton
    fun provideCartRepository(productsDatabase: ProductsDatabase): CartRepository {
        return CartRepository(productsDatabase)
    }

}
