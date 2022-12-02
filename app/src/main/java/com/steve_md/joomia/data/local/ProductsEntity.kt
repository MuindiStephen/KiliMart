package com.steve_md.joomia.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "products")
data class ProductsEntity (
    @PrimaryKey(autoGenerate = false)
    val int: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "price")
    val price: String,
    @ColumnInfo(name = "description")
    val description: String,
)