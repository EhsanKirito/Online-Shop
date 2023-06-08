package com.example.onlineshop.data.network.model.ui

import com.example.onlineshop.data.network.model.dto.product.Image

data class CategoryItem (
    val id: Int?,
    val name: String?,
    val description: String?,
    val image: Image?
)