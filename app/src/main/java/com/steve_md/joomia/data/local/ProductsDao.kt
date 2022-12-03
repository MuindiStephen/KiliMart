package com.steve_md.joomia.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.steve_md.joomia.model.ProductsItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface ProductsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductsItem>)

    // Fetch all data from the database on local db
    // coroutines not necessarily needed
    @Query("SELECT * FROM products")
    fun getAllProducts() : StateFlow<List<ProductsItem>>   // instead can replace it with LiveData

    // When the device is online, instead cached data will be deleted and replaced with another one
    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()

    // Get products from the local db based on the Searching
    @Query("SELECT * FROM products WHERE title LIKE :searchQuery OR price  LIKE :searchQuery")
    fun searchDatabase(searchQuery: String) : Flow<List<ProductsItem>>


}