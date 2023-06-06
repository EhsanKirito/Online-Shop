package com.example.onlineshop.data.network.model.dto.product

import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("results") val results:ArrayList<Product>
)