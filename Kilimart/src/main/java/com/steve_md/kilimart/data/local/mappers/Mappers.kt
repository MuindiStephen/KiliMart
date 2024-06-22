package com.steve_md.kilimart.data.local.mappers

import com.steve_md.kilimart.data.local.entity.CartEntity
import com.steve_md.kilimart.model.ProductsItem

/* Maps a data class into other data classes
*
* We are mapping ProductItem data class into CartEntity data class
* */

fun ProductsItem.toCartEntity() : CartEntity {
    return CartEntity(
        image = image,
        title = title,
        description = description,
        category = category,
        price = price
    )
}