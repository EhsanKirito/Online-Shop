package com.example.onlineshop.data.network.remotedatasource


import com.example.onlineshop.data.network.model.ui.ProductItem
import kotlinx.coroutines.flow.Flow


interface ShopRemoteDataSource {

    fun getProduct(id:Int): Flow<ProductItem>

    fun getNewestProducts(): Flow<List<ProductItem>>
    fun getMostViewedProducts(): Flow<List<ProductItem>>
    fun getBestProducts(): Flow<List<ProductItem>>
}