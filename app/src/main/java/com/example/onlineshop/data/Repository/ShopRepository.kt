package com.example.onlineshop.data.Repository

import com.example.onlineshop.data.network.model.ui.ProductItem
import com.example.onlineshop.data.network.remotedatasource.ShopRemoteDataSource
import com.example.onlineshop.data.network.safeapicall.ResponseState
import com.example.onlineshop.data.network.safeapicall.asResponseState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShopRepository @Inject constructor(
    private val shopRemoteDataSource: ShopRemoteDataSource
) {
    fun getProducts(page:Int): Flow<ResponseState<List<ProductItem>>> =shopRemoteDataSource.getProducts(page).asResponseState()

    fun getProduct(id:Int): Flow<ResponseState<List<ProductItem>>> =shopRemoteDataSource.getProducts(id).asResponseState()
}