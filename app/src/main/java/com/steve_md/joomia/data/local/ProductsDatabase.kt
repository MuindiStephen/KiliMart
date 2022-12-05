package com.steve_md.joomia.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.steve_md.joomia.model.ProductsItem


@Database(exportSchema = false, version = 1, entities = [ProductsItem::class])
abstract class ProductsDatabase : RoomDatabase() {

    // calling ProductsDao
    abstract fun productsDao(): ProductsDao

}