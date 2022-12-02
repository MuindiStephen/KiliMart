package com.steve_md.joomia.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(exportSchema = false, version = 1, entities = [ProductsEntity::class])
abstract class ProductsDatabase : RoomDatabase() {
    abstract fun productsDao(): ProductsDao
}