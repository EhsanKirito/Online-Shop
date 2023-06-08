package com.example.onlineshop.data.network.model.ui

import com.example.onlineshop.data.network.model.dto.product.Image

data class ProductItem(
    val id:Int?,
    val name:String?,
    val desc:String?,
    val price:String?,
    val imageUrl:String?,
    val imageUrls:List<Image?>?
)
