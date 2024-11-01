/*
 * Copyright (c)  Stephen Muindi
 */

package com.steve_md.kilimart.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.steve_md.kilimart.data.local.entity.CartEntity
import com.steve_md.kilimart.model.ProductsItem


/*
*  The Schema of room Database changes when more entities are added
* to the database
 */
@Database(exportSchema = false, version = 2, entities = [ProductsItem::class, CartEntity::class])
@TypeConverters
abstract class ProductsDatabase : RoomDatabase() {

    // calling ProductsDao
    abstract fun productsDao(): ProductsDao

    // calling CartDao
    abstract fun cartDao(): CartDao

   /* companion object {

        @Volatile
        private var INSTANCE: ProductsDatabase? = null

        fun getDatabase(context: Context) : ProductsDatabase {
            return INSTANCE?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    ProductsDatabase::class.java,
                    "products_database")
                    .build()
                INSTANCE = db
                db
            }
        }
    }*/



}