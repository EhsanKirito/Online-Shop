package com.example.onlineshop.data.network.service

import com.example.onlineshop.data.network.model.dto.category.CategoryDto
import com.example.onlineshop.data.network.model.dto.category.CategoryDtoItem
import com.example.onlineshop.data.network.model.dto.product.Product
import com.example.onlineshop.data.network.model.dto.product.ProductDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShopApiService {

    @GET("/wp-json/wc/v3/products/{id}")
    suspend fun getProduct(
        @Path("id") id: Int
    ): Response<Product>

    @GET("/wp-json/wc/v3/products")
    suspend fun getNewestProducts(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10,
        @Query("orderby") orderBy: String = "date"
    ): Response<ProductDto>

    @GET("/wp-json/wc/v3/products")
    suspend fun getMostViewedProducts(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10,
        @Query("orderby") orderBy: String = "popularity"
    ): Response<ProductDto>

    @GET("/wp-json/wc/v3/products")
    suspend fun getBestProducts(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10,
        @Query("orderby") orderBy: String = "rating"
    ): Response<ProductDto>

    @GET("/wp-json/wc/v3/products/categories")
    suspend fun getAllCategories(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10,
    ): Response<CategoryDto>

    @GET("/wp-json/wc/v3/products")
    suspend fun getCategoriesById(
        @Query("category") category:Int
    ): Response<ProductDto>
}