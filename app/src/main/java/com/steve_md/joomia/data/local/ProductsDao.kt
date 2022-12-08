package com.steve_md.joomia.data.local

import android.content.Context
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.steve_md.joomia.model.ProductsItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    // This function returns a unit that is data got from the network
    // and saves it in the local cache
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductsItem>)

    // Fetch all data from the database on local db
    // coroutines not necessarily needed because the result type is loaded cache from local db
    @Query("SELECT * FROM products")
    fun getAllProducts() : Flow<List<ProductsItem>>   // instead can replace it with LiveData

    // When the device is online, instead cached data will be deleted and replaced with another one
    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()

    // Get products from the local db based on the Searching
    // Returns a flow of result type
    @Query("SELECT * FROM products WHERE title LIKE :searchQuery OR price  LIKE :searchQuery")
    fun searchDatabase(searchQuery: String) : Flow<List<ProductsItem>>


    /*companion object {
        fun getDb(): ProductsDatabase {
            return ProductsDatabase.getDatabase(this)
        }
    }*/


}