package com.example.onlineshop.data.Repository

import com.example.onlineshop.data.network.model.ui.CategoryItem
import com.example.onlineshop.data.network.model.ui.ProductItem
import com.example.onlineshop.data.network.remotedatasource.ShopRemoteDataSource
import com.example.onlineshop.data.network.safeapicall.ResponseState
import com.example.onlineshop.data.network.safeapicall.asResponseState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShopRepository @Inject constructor(
    private val shopRemoteDataSource: ShopRemoteDataSource
) {
    fun getProduct(id:Int): Flow<ResponseState<ProductItem>> = shopRemoteDataSource.getProduct(id).asResponseState()

    fun getNewestProducts(): Flow<ResponseState<List<ProductItem>>> = shopRemoteDataSource.getNewestProducts().asResponseState()
    fun getMostViewedProducts(): Flow<ResponseState<List<ProductItem>>> = shopRemoteDataSource.getMostViewedProducts().asResponseState()
    fun getBestProducts(): Flow<ResponseState<List<ProductItem>>> = shopRemoteDataSource.getBestProducts().asResponseState()

    fun getAllCategories(): Flow<ResponseState<List<CategoryItem>>> = shopRemoteDataSource.getAllCategories().asResponseState()
    fun getCategoriesById(id:Int): Flow<ResponseState<List<ProductItem>>> = shopRemoteDataSource.getCategoriesById(id).asResponseState()
}