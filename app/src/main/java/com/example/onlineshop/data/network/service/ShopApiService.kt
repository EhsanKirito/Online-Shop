package com.example.onlineshop.data.network.service

import com.example.onlineshop.data.network.model.dto.product.Product
import com.example.onlineshop.data.network.model.dto.product.ProductDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShopApiService {

    @GET("/wp-json/wc/v3/products?page=1")
    suspend fun getProducts(
//        @Query("page" ) page:Int = 1
    ): Response<ProductDto>

    @GET("/wp-json/wc/v3/products/<id>")
    suspend fun getProduct(
        @Path("id") id: Int
    ): Response<Product>

}