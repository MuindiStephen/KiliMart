package com.steve_md.joomia.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.steve_md.joomia.model.ProductsItem


@Database(exportSchema = false, version = 1, entities = [ProductsItem::class])
abstract class ProductsDatabase : RoomDatabase() {

    // calling ProductsDao
    abstract fun productsDao(): ProductsDao

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