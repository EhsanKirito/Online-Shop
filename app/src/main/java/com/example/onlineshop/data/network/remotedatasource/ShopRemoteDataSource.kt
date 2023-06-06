package com.example.onlineshop.data.network.remotedatasource


import com.example.onlineshop.data.network.model.ui.ProductItem
import kotlinx.coroutines.flow.Flow


interface ShopRemoteDataSource {

    fun getProducts(page:Int): Flow<List<ProductItem>>

    fun getProduct(id:Int): Flow<ProductItem>
}