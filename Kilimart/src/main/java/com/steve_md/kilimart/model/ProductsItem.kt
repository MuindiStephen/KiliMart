package com.steve_md.kilimart.model


// ProductsItem DTO

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "products")
data class ProductsItem(
    @PrimaryKey val id: Int,
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
    val title: String
) : Parcelable