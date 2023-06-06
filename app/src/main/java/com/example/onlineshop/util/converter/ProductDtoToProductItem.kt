package com.example.onlineshop.util.converter

import com.example.onlineshop.data.network.model.dto.product.Product
import com.example.onlineshop.data.network.model.ui.ProductItem

fun ArrayList<Product>.ProductDtoToProductItem():List<ProductItem> {
    return this?.map { product->
        product.ProductToProductItem()
    } ?: emptyList()
}

fun Product.ProductToProductItem() = ProductItem(name, description, price, images?.get(0)?.src)