package com.steve_md.joomia.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// DTO

@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey(autoGenerate = true) val id:Int? = null,
    val image: String,
    val title:String,
    val price:Double,
    val category: String,
    val description: String,
)